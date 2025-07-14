package io.github.blockneko11.simpleconfig.api.holder.number;

import io.github.blockneko11.simpleconfig.impl.holder.number.DoubleConfigHolderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * 表示一个浮点型的配置项。
 * @see NumberConfigHolder
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface DoubleConfigHolder extends NumberConfigHolder<Double> {
    /**
     * 创建一个浮点型的配置项，默认值为 0.0D。
     * @return 一个浮点型的配置项
     */
    @NotNull
    static DoubleConfigHolder of() {
        return builder().build();
    }

    /**
     * 创建一个浮点型的配置项。
     * @param defaults 默认值
     * @return 一个浮点型的配置项
     */
    @NotNull
    static DoubleConfigHolder of(double defaults) {
        return of(() -> defaults);
    }

    /**
     * 创建一个浮点型的配置项。
     * @param defaults 默认值
     * @return 一个浮点型的配置项
     */
    @NotNull
    static DoubleConfigHolder of(@NotNull Supplier<Double> defaults) {
        return builder().defaults(defaults).build();
    }

    /**
     * 创建一个浮点型的配置项的构建器。
     * @return 一个浮点型的配置项的构建器
     */
    @NotNull
    static DoubleConfigHolderImpl.Builder builder() {
        return new DoubleConfigHolderImpl.Builder();
    }

    /**
     * 表示一个创建浮点型的配置项的构建器。
     * @see NumberConfigHolder.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder extends NumberConfigHolder.Builder<Double> {
        @Override
        default Builder defaults(@Nullable Double defaults) {
            return (Builder) NumberConfigHolder.Builder.super.defaults(defaults);
        }

        @Override
        Builder defaults(@NotNull Supplier<Double> defaults);

        @Override
        Builder min(@NotNull Double min);

        @Override
        Builder max(@NotNull Double max);

        @Override
        DoubleConfigHolder build();
    }
}
