package io.github.blockneko11.simpleconfig.impl.manager;

import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.api.manager.ConfigManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractConfigManager<T extends Config> implements ConfigManager<T> {
    private final Class<T> clazz;
    private T config = null;

    protected AbstractConfigManager(@NotNull Class<T> clazz) {
        this.clazz = clazz;
    }

    @NotNull
    @Override
    public Class<T> getClazz() {
        return this.clazz;
    }

    @Nullable
    @Override
    public T get() {
        return this.config;
    }

    @Override
    public void set(@Nullable T value) {
        this.config = value;
    }

    @Override
    public void close() throws Exception {
        this.config = null;
    }

    protected static abstract class Builder<T extends Config> implements ConfigManager.Builder<T> {
        protected final Class<T> clazz;

        protected Builder(@NotNull Class<T> clazz) {
            this.clazz = clazz;
        }
    }
}
