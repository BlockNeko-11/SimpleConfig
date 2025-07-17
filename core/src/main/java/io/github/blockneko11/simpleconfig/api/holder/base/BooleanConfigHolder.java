package io.github.blockneko11.simpleconfig.api.holder.base;

import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.base.BooleanConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * 表示一个布尔类型的配置项。
 * @see ConfigHolder
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface BooleanConfigHolder extends ConfigHolder<Boolean> {
    /**
     * 创建一个布尔类型的配置项，默认值为 false。
     * @return 一个布尔类型的配置项
     */
    @NotNull
    static BooleanConfigHolder of() {
        return builder().build();
    }

    /**
     * 创建一个布尔类型的配置项。
     * @param defaults 默认值
     * @return 一个布尔类型的配置项
     */
    @NotNull
    static BooleanConfigHolder of(boolean defaults) {
        return of(() -> defaults);
    }

    /**
     * 创建一个布尔类型的配置项。
     * @param defaults 默认值
     * @return 一个布尔类型的配置项
     */
    @NotNull
    static BooleanConfigHolder of(@NotNull Supplier<Boolean> defaults) {
        return builder().defaults(defaults).build();
    }

    /**
     * 创建一个布尔类型的配置项的构建器。
     * @return 一个布尔类型的配置项的构建器
     */
    @NotNull
    static BooleanConfigHolder.Builder builder() {
        return new BooleanConfigHolderImpl.Builder();
    }

    /**
     * 表示一个创建布尔类型的配置项的构建器。
     * @see ConfigHolder.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder extends ConfigHolder.Builder<Boolean, BooleanConfigHolder, Builder> {
    }
}
