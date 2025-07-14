package io.github.blockneko11.simpleconfig.impl.manager;

import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.api.manager.InMemoryConfigManager;
import org.jetbrains.annotations.NotNull;

public class InMemoryConfigManagerImpl<T extends Config> extends AbstractConfigManager<T> implements InMemoryConfigManager<T> {
    protected InMemoryConfigManagerImpl(@NotNull Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void load() throws ReflectiveOperationException {
        T config = getClazz().getConstructor().newInstance();
        set(config);
    }

    @Override
    public void save() throws Exception {
    }

    public static class Builder<T extends Config>
            extends AbstractConfigManager.Builder<T>
            implements InMemoryConfigManager.Builder<T> {
        public Builder(@NotNull Class<T> clazz) {
            super(clazz);
        }

        @Override
        public InMemoryConfigManager<T> build() {
            return new InMemoryConfigManagerImpl<>(clazz);
        }
    }
}
