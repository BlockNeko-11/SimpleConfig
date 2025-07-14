package io.github.blockneko11.simpleconfig.api.manager;

import io.github.blockneko11.simpleconfig.api.Config;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 表示一个管理配置类（实现了 {@link Config} 的类）的管理器。
 * <p>
 * 本接口实现了 {@link AutoCloseable} 接口，因此可以使用 try-with-resources 语法来关闭管理器。
 * @param <T> 配置类的类型
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface ConfigManager<T extends Config> extends AutoCloseable {
    /**
     * 获取配置类的类型。
     * @return 配置类的类型
     */
    @NotNull
    Class<T> getClazz();

    /**
     * 加载配置类的内容。
     * @throws Exception 当加载失败时抛出
     */
    void load() throws Exception;

    /**
     * 获取配置类的实例。
     * @return 配置类的实例
     */
    @Nullable
    T get();

    /**
     * 设置配置类的实例。
     * @param value 配置类的实例
     */
    void set(@Nullable T value);

    /**
     * 保存配置类的内容。
     * @throws Exception 当保存失败时抛出
     */
    void save() throws Exception;

    /**
     * 表示一个配置管理器的构建器。
     * @param <T> 配置类的类型
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder<T extends Config> {
        /**
         * 构建一个配置管理器。
         * @return 配置管理器
         */
        ConfigManager<T> build();
    }
}
