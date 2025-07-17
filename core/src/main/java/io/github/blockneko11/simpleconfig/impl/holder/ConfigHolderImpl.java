package io.github.blockneko11.simpleconfig.impl.holder;

import io.github.blockneko11.simpleconfig.api.adapter.ConfigValueAdapter;
import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ConfigHolderImpl<T> implements ConfigHolder<T> {
    private final Class<T> clazz;
    private final Supplier<T> defaults;
    private final ConfigValueParser<T> parser;
    private final ConfigValueSerializer<T> serializer;

    @Nullable
    private T value;

    private ConfigHolderImpl(@NotNull Class<T> clazz,
                               @NotNull Supplier<T> defaults,
                               @Nullable ConfigValueParser<T> parser,
                               @Nullable ConfigValueSerializer<T> serializer) {
        this.clazz = clazz;
        this.defaults = defaults;
        this.parser = parser;
        this.serializer = serializer;
    }

    protected ConfigHolderImpl(@NotNull Class<T> clazz,
                               @NotNull Supplier<T> defaults) {
        this(clazz, defaults, null, null);
    }

    @NotNull
    @Override
    public Class<T> getClazz() {
        return this.clazz;
    }

    @Override
    public T getDefaults() {
        return this.defaults.get();
    }

    @Nullable
    @Override
    public T get() {
        if (this.value == null) {
            T def = this.getDefaults();
            if (def == null) {
                throw new IllegalArgumentException("default value is null");
            }

            this.set(def);
        }

        return this.value;
    }

    public void set(@Nullable T value) {
        this.value = value;
    }

    @Nullable
    @Override
    public ConfigValueParser<T> getParser() {
        return this.parser;
    }

    @Nullable
    @Override
    public ConfigValueSerializer<T> getSerializer() {
        return this.serializer;
    }

    public static class Builder<T> extends AbstractBuilder<T> implements ConfigHolder.Builder<T> {
        private final Class<T> clazz;

        public Builder(@NotNull Class<T> clazz) {
            super(() -> null);
            this.clazz = clazz;
        }

        @Override
        public Builder<T> defaults(T defaults) {
            return (Builder<T>) super.defaults(defaults);
        }

        @Override
        public Builder<T> defaults(@NotNull Supplier<T> defaults) {
            return (Builder<T>) super.defaults(defaults);
        }

        @Override
        public Builder<T> adapter(@NotNull ConfigValueAdapter<T> adapter) {
            return (Builder<T>) super.adapter(adapter);
        }

        @Override
        public Builder<T> parser(@Nullable ConfigValueParser<T> parser) {
            return (Builder<T>) super.parser(parser);
        }

        @Override
        public Builder<T> serializer(@Nullable ConfigValueSerializer<T> serializer) {
            return (Builder<T>) super.serializer(serializer);
        }

        @Override
        public ConfigHolder<T> build() {
            return new ConfigHolderImpl<>(this.clazz, defaults, parser, serializer);
        }
    }
}
