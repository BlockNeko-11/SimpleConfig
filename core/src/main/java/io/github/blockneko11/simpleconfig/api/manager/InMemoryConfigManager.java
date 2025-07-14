package io.github.blockneko11.simpleconfig.api.manager;

import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.impl.manager.InMemoryConfigManagerImpl;
import org.jetbrains.annotations.NotNull;

/**
 * 表示一个内存配置管理器。
 * @param <T> 配置类的类型
 * @see ConfigManager
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface InMemoryConfigManager<T extends Config> extends ConfigManager<T> {
    /**
     * 创建一个内存配置管理器。
     * @param clazz 配置类的类型
     * @return 内存配置管理器
     * @param <T> 配置类的类型
     */
    static <T extends Config> Builder<T> builder(@NotNull Class<T> clazz) {
        return new InMemoryConfigManagerImpl.Builder<>(clazz);
    }

    /**
     * 表示一个内存配置管理器的构建器。
     * @param <T> 配置类的类型
     * @see ConfigManager.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder<T extends Config> extends ConfigManager.Builder<T> {
        @Override
        InMemoryConfigManager<T> build();
    }
}
