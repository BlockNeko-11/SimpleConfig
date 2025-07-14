package io.github.blockneko11.simpleconfig.api.holder.number;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * 表示一个数字类型的配置对象的构造函数，并作为函数式接口使用。
 * @param <N> 数字的类型
 */
@FunctionalInterface
public interface NumberConfigHolderConstructor<N extends Number> {
    /**
     * 创建一个数字类型的配置对象。
     * @param defaults 默认值
     * @param min 最小值限制
     * @param max 最大值限制
     * @return 数字类型的配置对象
     */
    @NotNull
    NumberConfigHolder<N> create(@NotNull Supplier<N> defaults, @NotNull N min, @NotNull N max);
}
