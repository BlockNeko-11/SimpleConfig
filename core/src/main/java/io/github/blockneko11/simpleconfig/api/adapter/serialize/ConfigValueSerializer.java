package io.github.blockneko11.simpleconfig.api.adapter.serialize;

import org.jetbrains.annotations.Nullable;

/**
 * 表示一个将配置值序列化为对象的序列化器。
 * @param <T> 配置值的类型
 * @author BlockNeko-11
 * @since 1.1.0
 */
@FunctionalInterface
public interface ConfigValueSerializer<T> {
    /**
     * 将配置值序列化为对象。
     * @param value 配置值
     * @return 序列化后的对象
     */
    @Nullable
    Object serialize(T value);
}
