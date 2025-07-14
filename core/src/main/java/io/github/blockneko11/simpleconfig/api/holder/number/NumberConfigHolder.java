package io.github.blockneko11.simpleconfig.api.holder.number;

import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * 表示一个数字类型的配置项。
 * @param <N> 数字的类型
 * @see ConfigHolder
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface NumberConfigHolder<N extends Number> extends ConfigHolder<N> {
    /**
     * 获取数字的最小值限制。
     * @return 数字的最小值，实现默认返回类型的最小值
     */
    @NotNull
    N getMin();

    /**
     * 获取数字的最大值限制。
     * @return 数字的最大值，实现默认返回类型的最大值
     */
    @NotNull
    N getMax();

    /**
     * 检查数字是否在限制范围内。
     * @param value 数字
     * @return 数字是否在限制范围内
     */
    boolean isInRange(N value);

    /**
     * 表示一个创建数字类型的配置项的构建器
     * @param <N> 数字类型
     * @see ConfigHolder.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder<N extends Number> extends ConfigHolder.Builder<N> {
        @Override
        default Builder<N> defaults(@Nullable N defaults) {
            return (Builder<N>) ConfigHolder.Builder.super.defaults(defaults);
        }

        @Override
        Builder<N> defaults(@NotNull Supplier<N> defaults);

        /**
         * 设置数字的最小值限制。
         * @param min 最小值
         * @return 自身
         */
        Builder<N> min(@NotNull N min);

        /**
         * 设置数字的最大值限制。
         * @param max 最大值
         * @return 自身
         */
        Builder<N> max(@NotNull N max);

        @Override
        NumberConfigHolder<N> build();
    }
}
