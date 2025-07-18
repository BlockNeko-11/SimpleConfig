package io.github.blockneko11.simpleconfig.api.holder.number;

import io.github.blockneko11.simpleconfig.impl.holder.number.IntegerConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * 表示一个整型的配置项。
 * @see NumberConfigHolder
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface IntegerConfigHolder extends NumberConfigHolder<Integer> {
    /**
     * 创建一个整型的配置项，默认值为 0.0D。
     * @return 一个整型的配置项
     */
    @NotNull
    static IntegerConfigHolder of() {
        return builder().build();
    }

    /**
     * 创建一个整型的配置项。
     * @param defaults 默认值
     * @return 一个整型的配置项
     */
    @NotNull
    static IntegerConfigHolder of(int defaults) {
        return of(() -> defaults);
    }

    /**
     * 创建一个整型的配置项。
     * @param defaults 默认值
     * @return 一个整型的配置项
     */
    @NotNull
    static IntegerConfigHolder of(@NotNull Supplier<Integer> defaults) {
        return builder().defaults(defaults).build();
    }

    /**
     * 创建一个整型的配置项的构建器。
     * @return 一个整型的配置项的构建器
     */
    @NotNull
    static IntegerConfigHolder.Builder builder() {
        return new IntegerConfigHolderImpl.Builder();
    }

    /**
     * 表示一个创建整型的配置项的构建器。
     * @see NumberConfigHolder.Builder
     * @author BlockNeko-11
     * @since 1.1.0
     */
    interface Builder extends NumberConfigHolder.Builder<Integer, IntegerConfigHolder, Builder> {
    }
}
