package io.github.blockneko11.simpleconfig.api.holder;

import io.github.blockneko11.simpleconfig.impl.holder.ConfigHolderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
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

    // builder

    /**
     * 创建一个配置项构建器。
     * @param clazz 配置项的类型
     * @return 一个配置项构建器
     * @param <T> 配置项的类型
     */
    @NotNull
    static <T> ConfigHolder.Builder<T> builder(@NotNull Class<T> clazz) {
        return new ConfigHolderImpl.Builder<>(clazz);
    }

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
    @Nullable
    T get();

    // getter operations

    /**
     * 检查配置项的值是否为 null
     * @return 配置项的值是否为 null
     * @see #get()
     */
    default boolean isPresent() {
        return get() != null;
    }

    /**
     * 获取 {@link Optional} 包装后的配置项的值。
     * @return {@link Optional} 包装后的配置项的值
     * @see #get()
     */
    @NotNull
    default Optional<T> getOptional() {
        return Optional.ofNullable(get());
    }

    /**
     * 获取配置项的默认值。
     * @return 配置项的默认值。在实现中，可能会抛出 {@link IllegalArgumentException}。
     */
    T getDefaults();

    /**
     * 检查配置项的值是否为默认值。
     * @return 配置项的值是否为默认值
     * @see #get()
     * @see #getDefaults()
     */
    default boolean isDefault() {
        return Objects.equals(get(), getDefaults());
    }

    /**
     * 获取配置项的值，并映射为另一个类型。
     * @param mapper 映射函数
     * @return 映射后的值
     * @param <U> 映射后的类型
     */
    @Nullable
    default <U> U map(@NotNull Function<T, U> mapper) {
        T value = get();

        if (value == null) {
            return null;
        }

        return mapper.apply(value);
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
        return Optional.ofNullable(map(mapper));
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
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder<T> {
        /**
         * 设置默认值。
         * @param defaults 默认值
         * @return 自身
         */
        default Builder<T> defaults(@Nullable T defaults) {
            return defaults(() -> defaults);
        }

        /**
         * 设置默认值。
         * @param defaults 默认值
         * @return 自身
         */
        Builder<T> defaults(@NotNull Supplier<T> defaults);

        /**
         * 构建
         * @return 配置项
         */
        ConfigHolder<T> build();
    }
}
