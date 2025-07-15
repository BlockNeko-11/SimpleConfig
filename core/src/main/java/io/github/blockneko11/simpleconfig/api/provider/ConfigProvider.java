package io.github.blockneko11.simpleconfig.api.provider;

import java.util.Map;

/**
 * 表示一个提供配置处理的 API。
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface ConfigProvider {
    /**
     * 解析配置。
     * @param config 配置
     * @return 解析后的配置
     */
    Map<String, Object> parse(String config);

    /**
     * 将配置序列化为字符串。
     * @param config 配置
     * @return 序列化后的字符串
     */
    String serialize(Map<String, Object> config);
}
