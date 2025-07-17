package io.github.blockneko11.simpleconfig.impl.holder;

import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class ConfigHolderImpl<T> implements ConfigHolder<T> {
    private final Class<T> clazz;
    private final Supplier<T> defaults;

    @Nullable
    private T value;

    protected ConfigHolderImpl(@NotNull Class<T> clazz,
                               @NotNull Supplier<T> defaults) {
        this.clazz = clazz;
        this.defaults = defaults;
    }

    @NotNull
    @Override
    public Class<T> getClazz() {
        return this.clazz;
    }

    @NotNull
    @Override
    public T get() {
        if (this.value == null) {
            this.set(null);
        }

        return this.value;
    }

    @NotNull
    @Override
    public T getDefaults() {
        return this.defaults.get();
    }

    public void set(@Nullable T value) {
        if (value == null) {
            this.value = this.getDefaults();
            return;
        }

        this.value = value;
    }

    public abstract static class BuilderImpl<
            T,
            Result extends ConfigHolder<T>,
            Impl extends Builder<T, Result, Impl>
            >
            implements Builder<T, Result, Impl> {

        protected Supplier<T> defaults;

        protected BuilderImpl(@NotNull Supplier<T> defaults) {
            this.defaults = defaults;
        }

        @Override
        public Impl defaults(@NotNull Supplier<T> defaults) {
            this.defaults = defaults;
            return (Impl) this;
        }
    }
}
