package io.github.blockneko11.simpleconfig.api.holder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 表示一个配置项。
 * @param <T> 配置项的类型
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface ConfigHolder<T> {
    // metadata

    /**
     * 获取配置项的类型。
     * @return 配置项的类型
     */
    @NotNull
    Class<T> getClazz();

    // getter

    /**
     * 获取配置项的值。
     * @return 配置项的值。如果为 null，则返回默认值。
     */
    @NotNull
    T get();

    // getter operations

    /**
     * 获取 {@link Optional} 包装后的配置项的值。
     * @return {@link Optional} 包装后的配置项的值
     * @see #get()
     * @since 1.1.0
     */
    @NotNull
    default Optional<T> getOptional() {
        return Optional.of(this.get());
    }

    /**
     * 获取配置项的默认值。
     * @return 配置项的默认值。在实现中，可能会抛出 {@link IllegalArgumentException}。
     */
    @NotNull
    T getDefaults();

    /**
     * 检查配置项的值是否为默认值。
     * @return 配置项的值是否为默认值
     * @see #get()
     * @since 1.1.0
     */
    default boolean isDefault() {
        return this.get() == this.getDefaults();
    }

    /**
     * 获取配置项的值，并映射为另一个类型。
     * @param mapper 映射函数
     * @return 映射后的值
     * @param <U> 映射后的类型
     * @since 1.1.0
     */
    @NotNull
    default <U> U map(@NotNull Function<T, U> mapper) {
        return mapper.apply(this.get());
    }

    /**
     * 获取配置项的值，映射为另一个类型，再包装为 {@link Optional}。
     * @param mapper 映射函数
     * @return 映射后的值
     * @param <U> 映射后的类型
     * @see #map(Function) 
     */
    @NotNull
    default <U> Optional<U> mapOptional(@NotNull Function<T, U> mapper) {
        return Optional.of(this.map(mapper));
    }

    // setter

    /**
     * 设置配置项的值。
     * @param value 值
     */
    void set(@Nullable T value);

    /**
     * 表示一个创建配置项的构建器。
     * @param <T> 配置项的类型
     * @param <Result> 创建的配置项的类型
     * @param <Impl> 构建器的实现类型
     * @author BlockNeko-11
     * @since 1.1.0
     */
    interface Builder<
            T,
            Result extends ConfigHolder<T>,
            Impl extends Builder<T, Result, Impl>
            > {
        /**
         * 设置默认值。
         *
         * @param defaults 默认值
         * @return 自身
         */
        default Impl defaults(@Nullable T defaults) {
            return defaults(() -> defaults);
        }

        /**
         * 设置默认值。
         *
         * @param defaults 默认值
         * @return 自身
         */
        Impl defaults(@NotNull Supplier<T> defaults);

        /**
         * 构建
         *
         * @return 配置项
         */
        Result build();
    }
}
