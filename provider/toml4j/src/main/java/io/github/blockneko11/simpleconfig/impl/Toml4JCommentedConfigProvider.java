package io.github.blockneko11.simpleconfig.impl;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import io.github.blockneko11.simpleconfig.api.provider.CommentedConfigProvider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Toml4JCommentedConfigProvider extends Toml4JConfigProvider implements CommentedConfigProvider {
    public Toml4JCommentedConfigProvider() {
        super();
    }

    public Toml4JCommentedConfigProvider(Toml toml, TomlWriter writer) {
        super(toml, writer);
    }

    @Override
    public String serialize(Map<String, Object> config) {
        return this.serialize(config, Collections.emptyMap());
    }

    @Override
    public String serialize(Map<String, Object> config, Map<String, List<String>> comments) {
        StringBuilder sb = new StringBuilder();

        List<String> root = comments.get("__root__");
        if (root != null) {
            for (String line : root) {
                sb.append("# ").append(line).append("\n");
            }

            sb.append("\n");
        }

        for (Map.Entry<String, Object> entry : config.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            List<String> lines = comments.get(key);
            boolean hasAnyComments = (lines != null);
            if (hasAnyComments) {
                for (String line : lines) {
                    sb.append("# ").append(line).append("\n");
                }
            }

            Map<String, Object> map = Collections.singletonMap(key, value);
            sb.append(writer.write(map));
            sb.append("\n");
        }

        return sb.toString();
    }
}
