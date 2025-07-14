package io.github.blockneko11.simpleconfig.impl.holder;

import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ConfigHolderImpl<T> implements ConfigHolder<T> {
    private final Class<T> clazz;
    private final Supplier<T> defaults;

    @Nullable
    private T value;

    protected ConfigHolderImpl(@NotNull Class<T> clazz, @NotNull Supplier<T> defaults) {
        this.clazz = clazz;
        this.defaults = defaults;
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
        public ConfigHolder<T> build() {
            return new ConfigHolderImpl<>(this.clazz, defaults);
        }
    }
}
