package io.github.blockneko11.simpleconfig.impl.manager;

import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.annotation.Alias;
import io.github.blockneko11.simpleconfig.api.annotation.Comment;
import io.github.blockneko11.simpleconfig.api.annotation.Ignore;
import io.github.blockneko11.simpleconfig.api.holder.collection.ListConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.collection.MapConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.LongConfigHolder;
import io.github.blockneko11.simpleconfig.api.manager.FileConfigManager;
import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.DoubleConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.IntegerConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.base.BooleanConfigHolder;
import io.github.blockneko11.simpleconfig.api.provider.CommentedConfigProvider;
import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;
import io.github.blockneko11.simpleconfig.util.reflect.ReflectUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileConfigManagerImpl<T extends Config>
        extends ConfigManagerImpl<T>
        implements FileConfigManager<T> {
    private final ConfigProvider provider;
    private final File file;

    protected FileConfigManagerImpl(@NotNull Class<T> clazz,
                                    @NotNull ConfigProvider provider,
                                    @NotNull File file) {
        super(clazz);
        this.provider = provider;
        this.file = file;
    }

    @NotNull
    @Override
    public ConfigProvider getProvider() {
        return this.provider;
    }

    @Override
    public void load() throws IOException, ReflectiveOperationException {
        if (!this.file.exists()) {
            if (!this.file.getParentFile().exists()) {
                this.file.getParentFile().mkdirs();
            }

            this.file.createNewFile();
            set(ReflectUtil.newInstance(getClazz()));
        } else {
            StringBuilder builder = new StringBuilder();

            try (FileInputStream fis = new FileInputStream(this.file);
                 InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(isr)) {
                String line;

                while ((line = br.readLine()) != null) {
                    builder.append(line).append("\n");
                }
            }

            Map<String, Object> parsed = this.provider.parse(builder.toString());
            set(fromMap(getClazz(), parsed));
        }
    }

    private static <T> T fromMap(Class<T> clazz, Map<String, Object> map) throws ReflectiveOperationException {
        T instance = ReflectUtil.newInstance(clazz);

        if (instance == null) {
            return null;
        }

        for (Field f : clazz.getFields()) {
            if (!ConfigHolder.class.isAssignableFrom(f.getType())) {
                continue;
            }

            if (f.isAnnotationPresent(Ignore.class)) {
                continue;
            }


            Alias alias = f.getAnnotation(Alias.class);
            String key;
            if (alias != null) {
                key = alias.value();
            } else {
                key = f.getName();
            }

            Object value = map.get(key);
            ConfigHolder<?> object = (ConfigHolder<?>) f.get(instance);
            Class<?> valueClass = object.getClazz();

            if (value == null) {
                object.set(null);
                continue;
            }

            if (valueClass == Boolean.class) {
                ((BooleanConfigHolder) object).set((Boolean) value);
                continue;
            }

            if (valueClass == Integer.class) {
                ((IntegerConfigHolder) object).set(((Number) value).intValue());
                continue;
            }

            if (valueClass == Long.class) {
                ((LongConfigHolder) object).set(((Number) value).longValue());
                continue;
            }

            if (valueClass == Double.class) {
                ((DoubleConfigHolder) object).set(((Number) value).doubleValue());
                continue;
            }

            if (valueClass == String.class) {
                ((ConfigHolder<String>) object).set((String) value);
                continue;
            }

            if (valueClass == List.class) {
                ((ListConfigHolder<Object>) object).set((List<Object>) value);
                continue;
            }

            if (valueClass == Map.class) {
                ((MapConfigHolder<Object, Object>) object).set((Map<Object, Object>) value);
                continue;
            }

            ConfigValueParser<Object> parser = ((ConfigHolder<Object>) object).getParser();
            if (parser != null) {
                ((ConfigHolder<Object>) object).set(parser.parse(value));
                continue;
            }

            throw new IllegalArgumentException("unable to parse value for " + key);

//            ((ConfigHolder<Object>) object).set(fromMap(valueClass, (Map<String, Object>) value));
        }

        return instance;
    }

    @Override
    public void save() throws IOException, ReflectiveOperationException {
        Map<String, Object> config = toMap(get());
        String serialized;
        if (this.provider instanceof CommentedConfigProvider) {
            serialized = ((CommentedConfigProvider) this.provider).serialize(config, getComments(get()));
        } else {
            serialized = this.provider.serialize(config);
        }

        try (FileOutputStream fos = new FileOutputStream(this.file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(serialized);
            bw.flush();
        }
    }

    private static <T> Map<String, Object> toMap(T config) throws ReflectiveOperationException {
        if (config == null) {
            return Collections.emptyMap();
        }

        Class<?> clazz = config.getClass();
        Map<String, Object> map = new LinkedHashMap<>();
        for (Field f : clazz.getFields()) {
            if (!ConfigHolder.class.isAssignableFrom(f.getType())) {
                continue;
            }

            if (f.isAnnotationPresent(Ignore.class)) {
                continue;
            }

            Alias alias = f.getAnnotation(Alias.class);
            String key;
            if (alias != null) {
                key = alias.value();
            } else {
                key = f.getName();
            }

            ConfigHolder<?> object = (ConfigHolder<?>) f.get(config);
            Class<?> valueClass = object.getClazz();
            Object value = object.get();

            if (value == null) {
                map.put(key, null);
                continue;
            }

            if (valueClass == Boolean.class) {
                map.put(key, ((BooleanConfigHolder) object).get());
                continue;
            }

            if (valueClass == Integer.class) {
                map.put(key, ((IntegerConfigHolder) object).get());
                continue;
            }

            if (valueClass == Double.class) {
                map.put(key, ((DoubleConfigHolder) object).get());
                continue;
            }

            if (valueClass == String.class) {
                map.put(key, ((ConfigHolder<String>) object).get());
                continue;
            }

            if (valueClass == List.class) {
                map.put(key, ((ListConfigHolder) object).get());
                continue;
            }

            if (valueClass == Map.class) {
                map.put(key, ((MapConfigHolder) object).get());
                continue;
            }

            ConfigValueSerializer<Object> serializer = ((ConfigHolder<Object>) object).getSerializer();
            if (serializer != null) {
                map.put(key, serializer.serialize(((ConfigHolder<Object>) object).get()));
                continue;
            }

            throw new IllegalArgumentException("unable to serialize value for " + key);
        }

        return map;
    }

    private static <T extends Config> Map<String, List<String>> getComments(T config) {
        if (config == null) {
            return Collections.emptyMap();
        }

        Class<?> clazz = config.getClass();
        Map<String, List<String>> comments = new LinkedHashMap<>();

        Comment root = clazz.getAnnotation(Comment.class);
        if (root != null) {
            comments.put("__root__", Arrays.asList(root.value()));
        }

        for (Field f : clazz.getFields()) {
            if (!ConfigHolder.class.isAssignableFrom(f.getType())) {
                continue;
            }

            if (f.isAnnotationPresent(Ignore.class)) {
                continue;
            }

            Alias alias = f.getAnnotation(Alias.class);
            String name;
            if (alias != null) {
                name = alias.value();
            } else {
                name = f.getName();
            }

            Comment comment = f.getAnnotation(Comment.class);
            if (comment != null) {
                comments.put(name, Arrays.asList(comment.value()));
            }
        }

        return comments;
    }

    @NotNull
    @Override
    public File getFile() {
        return this.file;
    }

    public static class Builder<T extends Config>
            extends ConfigManagerImpl.Builder<T>
            implements FileConfigManager.Builder<T> {
        private File file;
        private ConfigProvider provider;

        public Builder(@NotNull Class<T> clazz) {
            super(clazz);
        }

        @Override
        public FileConfigManager.Builder<T> provider(@NotNull ConfigProvider provider) {
            this.provider = provider;
            return this;
        }

        @Override
        public FileConfigManager.Builder<T> file(@NotNull File file) {
            this.file = file;
            return this;
        }

        @Override
        public FileConfigManager<T> build() {
            this.checkNotNull();
            return new FileConfigManagerImpl<>(clazz, this.provider, this.file);
        }

        private void checkNotNull() {
            if (this.provider == null) {
                throw new IllegalArgumentException("provider cannot be null");
            }

            if (this.file == null) {
                throw new IllegalArgumentException("file cannot be null");
            }
        }
    }
}
