package io.github.blockneko11.simpleconfig.impl.provider;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;
import io.github.blockneko11.simpleconfig.api.provider.CommentedConfigProvider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TCCommentedConfigProvider extends TCConfigProvider implements CommentedConfigProvider {
    @Override
    public String serialize(Map<String, Object> config) {
        return this.serialize(config, Collections.emptyMap());
    }

    @Override
    public String serialize(Map<String, Object> config, Map<String, List<String>> comments) {
        Config tcConfig = ConfigFactory.empty();

        for (Map.Entry<String, Object> entry : config.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            ConfigValue tcValue;
            if (value == null || value instanceof Boolean || value instanceof String || value instanceof Number) {
                tcValue = ConfigValueFactory.fromAnyRef(value);
            } else if (value instanceof List) {
                tcValue = ConfigValueFactory.fromIterable((List<?>) value);
            } else if (value instanceof Map) {
                tcValue = ConfigValueFactory.fromMap((Map<String, ?>) value);
            } else {
                tcValue = ConfigValueFactory.fromAnyRef(String.valueOf(value));
            }

            List<String> lines = comments.get(key);
            if (lines != null) {
                tcValue = tcValue.withOrigin(tcValue.origin().withComments(lines));
            }

            tcConfig = tcConfig.withValue(key, tcValue);
        }

        String content = tcConfig.root().render(TCOptions.RENDER_OPTIONS.get());

        StringBuilder result = new StringBuilder();
        List<String> header = comments.get("__root__");
        if (header != null) {
            for (String line : header) {
                result.append("# ").append(line).append("\n");
            }

            result.append("\n");
        }

        return result.append(content).toString();
    }
}
