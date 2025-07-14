package io.github.blockneko11.simpleconfig.impl.manager;

import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.api.annotation.Alias;
import io.github.blockneko11.simpleconfig.api.annotation.Ignore;
import io.github.blockneko11.simpleconfig.api.holder.collection.ListConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.collection.MapConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.LongConfigHolder;
import io.github.blockneko11.simpleconfig.api.manager.FileConfigManager;
import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.DoubleConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.IntegerConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.base.BooleanConfigHolder;
import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;
import io.github.blockneko11.simpleconfig.util.reflect.ReflectUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileConfigManagerImpl<T extends Config> extends AbstractConfigManager<T> implements FileConfigManager<T> {
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
                    builder.append(line);
                }
            }

            Map<String, Object> config = this.provider.parse(builder.toString());
            set(fromMap(getClazz(), config));
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

            Object value;
            Alias alias = f.getAnnotation(Alias.class);
            if (alias != null) {
                value = map.get(alias.value());
            } else {
                value = map.get(f.getName());
            }

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
                ((IntegerConfigHolder) object).set((Integer) value);
                continue;
            }

            if (valueClass == Long.class) {
                ((LongConfigHolder) object).set((Long) value);
                continue;
            }

            if (valueClass == Double.class) {
                ((DoubleConfigHolder) object).set((Double) value);
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

            ((ConfigHolder<Object>) object).set(fromMap(valueClass, (Map<String, Object>) value));
        }

        return instance;
    }

    @Override
    public void save() throws IOException, ReflectiveOperationException {
        String config = this.provider.serialize(toMap(get()));

        try (FileOutputStream fos = new FileOutputStream(this.file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(config);
            bw.flush();
        }
    }

    private static <T> Map<String, Object> toMap(T config) throws ReflectiveOperationException {
        if (config == null) {
            return new LinkedHashMap<>();
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
            String name;
            if (alias != null) {
                name = alias.value();
            } else {
                name = f.getName();
            }

            ConfigHolder<?> object = (ConfigHolder<?>) f.get(config);
            Class<?> valueClass = object.getClazz();
            Object value = object.get();

            if (value == null) {
                map.put(name, null);
                continue;
            }

            if (valueClass == Boolean.class) {
                map.put(name, ((BooleanConfigHolder) object).get());
                continue;
            }

            if (valueClass == Integer.class) {
                map.put(name, ((IntegerConfigHolder) object).get());
                continue;
            }

            if (valueClass == Double.class) {
                map.put(name, ((DoubleConfigHolder) object).get());
                continue;
            }

            if (valueClass == String.class) {
                map.put(name, ((ConfigHolder<String>) object).get());
                continue;
            }

            if (valueClass == List.class) {
                map.put(name, ((ListConfigHolder) object).get());
                continue;
            }

            if (valueClass == Map.class) {
                map.put(name, ((MapConfigHolder) object).get());
                continue;
            }

            map.put(name, toMap(value));
        }

        return map;
    }

    @NotNull
    @Override
    public File getFile() {
        return this.file;
    }

    public static class Builder<T extends Config>
            extends AbstractConfigManager.Builder<T>
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
