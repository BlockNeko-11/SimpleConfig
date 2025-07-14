package io.github.blockneko11.simpleconfig.api.manager;

import io.github.blockneko11.simpleconfig.api.Config;
import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;
import io.github.blockneko11.simpleconfig.impl.manager.FileConfigManagerImpl;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * 表示一个文件配置管理器。
 * @param <T> 配置类的类型
 * @see ConfigManager
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface FileConfigManager<T extends Config> extends ConfigManager<T> {
    /**
     * 创建一个文件配置管理器。
     * @param clazz 配置类的类型
     * @return 文件配置管理器
     * @param <T> 配置类的类型
     */
    static <T extends Config> Builder<T> builder(@NotNull Class<T> clazz) {
        return new FileConfigManagerImpl.Builder<>(clazz);
    }

    /**
     * 获取一个 {@link ConfigProvider} 实例。
     * @return 一个 {@link ConfigProvider} 实例
     */
    @NotNull
    ConfigProvider getProvider();

    /**
     * 获取配置文件。
     * @return 配置文件
     */
    @NotNull
    File getFile();

    @Override
    void load() throws IOException, ReflectiveOperationException;

    @Override
    void save() throws IOException, ReflectiveOperationException;

    /**
     * 表示一个文件配置管理器的构建器。
     * @param <T> 配置类的类型
     * @see ConfigManager.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder<T extends Config> extends ConfigManager.Builder<T> {
        /**
         * 设置 {@link ConfigProvider} 实例。
         * @param provider {@link ConfigProvider} 实例
         * @return 自身
         */
        Builder<T> provider(@NotNull ConfigProvider provider);

        /**
         * 设置配置文件。
         * @param file 配置文件
         * @return 自身
         */
        Builder<T> file(@NotNull File file);

        @Override
        FileConfigManager<T> build();
    }
}
