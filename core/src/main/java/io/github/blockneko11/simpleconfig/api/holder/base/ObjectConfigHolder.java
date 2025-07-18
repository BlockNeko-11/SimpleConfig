package io.github.blockneko11.simpleconfig.api.holder.base;

import io.github.blockneko11.simpleconfig.api.adapter.ConfigValueAdapter;
import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.base.ObjectConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

/**
 * 表示一个对象类型的配置项。
 * @param <T> 配置项的类型
 * @see ConfigHolder
 * @author BlockNeko-11
 * @since 1.1.0
 */
public interface ObjectConfigHolder<T> extends ConfigHolder<T> {
    /**
     * 创建一个对象类型的配置项的构建器。
     * @return 一个对象类型的配置项的构建器
     */
    static <T> ObjectConfigHolder.Builder<T> builder(@NotNull Class<T> clazz) {
        return new ObjectConfigHolderImpl.Builder<>(clazz);
    }

    /**
     * 获取对象类型的配置项的解析器。
     * @return 解析器
     */
    @NotNull
    ConfigValueParser<T> getParser();

    /**
     * 获取对象类型的配置项的序列化器。
     * @return 序列化器
     */
    @NotNull
    ConfigValueSerializer<T> getSerializer();

    /**
     * 表示一个创建对象类型的配置项的构建器。
     * @param <T> 配置项的类型
     * @author BlockNeko-11
     * @since 1.1.0
     */
    interface Builder<T> extends ConfigHolder.Builder<T, ObjectConfigHolder<T>, Builder<T>> {
        default Builder<T> adapter(@NotNull ConfigValueAdapter<T> adapter) {
            return this.parser(adapter).serializer(adapter);
        }

        /**
         * 设置对象类型的配置项的解析器。
         * @param parser 解析器
         * @return 自身
         */
        Builder<T> parser(@NotNull ConfigValueParser<T> parser);

        /**
         * 设置对象类型的配置项的序列化器。
         * @param serializer 序列化器
         * @return 自身
         */
        Builder<T> serializer(@NotNull ConfigValueSerializer<T> serializer);
    }
}
