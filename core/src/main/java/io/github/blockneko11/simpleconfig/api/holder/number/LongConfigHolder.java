package io.github.blockneko11.simpleconfig.api.holder.number;

import io.github.blockneko11.simpleconfig.impl.holder.number.LongConfigHolderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * 表示一个长整型（long）的配置项。
 * @see NumberConfigHolder
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface LongConfigHolder extends NumberConfigHolder<Long> {
    /**
     * 创建一个长整型（long）的配置项，默认值为 0.0D。
     * @return 一个长整型（long）的配置项
     */
    @NotNull
    static LongConfigHolder of() {
        return builder().build();
    }

    /**
     * 创建一个长整型（long）的配置项。
     * @param defaults 默认值
     * @return 一个长整型（long）的配置项
     */
    @NotNull
    static LongConfigHolder of(long defaults) {
        return of(() -> defaults);
    }

    /**
     * 创建一个长整型（long）的配置项。
     * @param defaults 默认值
     * @return 一个长整型（long）的配置项
     */
    @NotNull
    static LongConfigHolder of(@NotNull Supplier<Long> defaults) {
        return builder().defaults(defaults).build();
    }

    /**
     * 创建一个长整型（long）的配置项的构建器。
     * @return 一个长整型（long）的配置项的构建器
     */
    @NotNull
    static LongConfigHolder.Builder builder() {
        return new LongConfigHolderImpl.Builder();
    }

    /**
     * 表示一个创建长整型（long）的配置项的构建器。
     * @see NumberConfigHolder.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder extends NumberConfigHolder.Builder<Long> {
        @Override
        default Builder defaults(@Nullable Long defaults) {
            return (Builder) NumberConfigHolder.Builder.super.defaults(defaults);
        }

        @Override
        Builder defaults(@NotNull Supplier<Long> defaults);

        @Override
        Builder min(@NotNull Long min);

        @Override
        Builder max(@NotNull Long max);

        @NotNull
        LongConfigHolder build();
    }
}
