package io.github.blockneko11.simpleconfig.api.adapter.parse;

import org.jetbrains.annotations.NotNull;

/**
 * 表示一个将对象解析为配置值的解析器。
 * @param <T>
 * @author BlockNeko-11
 * @since 1.1.0
 */
@FunctionalInterface
public interface ConfigValueParser<T> {
    /**
     * 将对象解析为配置值。
     * @param value 对象
     * @return 配置值
     */
    @NotNull
    T parse(@NotNull Object value);
}
