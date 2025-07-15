package io.github.blockneko11.simpleconfig.api.provider;

import java.util.List;
import java.util.Map;

/**
 * 表示一个提供配置处理并提供注释输出的 API。
 * @author BlockNeko-11
 * @since 1.0.1
 */
public interface CommentConfigProvider extends ConfigProvider {
    /**
     * 将配置序列化为字符串，并添加注释。
     * @param config 配置
     * @param comments 注释
     * @return 序列化后的字符串
     */
    String serialize(Map<String, Object> config, Map<String, List<String>> comments);
}
