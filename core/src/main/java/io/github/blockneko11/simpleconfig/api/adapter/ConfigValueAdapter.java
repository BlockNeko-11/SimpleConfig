package io.github.blockneko11.simpleconfig.api.adapter;

import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;

/**
 * 表示一个配置值的适配器，用于将对象解析和配置值序列化。
 * @param <T> 配置值的类型
 * @author BlockNeko-11
 * @since 1.1.0
 */
public interface ConfigValueAdapter<T> extends ConfigValueParser<T>, ConfigValueSerializer<T> {
}
