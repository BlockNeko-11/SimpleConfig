package io.github.blockneko11.simpleconfig.api.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommentConfigProvider extends ConfigProvider {
    @Override
    default String serialize(Map<String, Object> config) {
        return serialize(config, new HashMap<>());
    }

    String serialize(Map<String, Object> config, Map<String, List<String>> comments);
}
