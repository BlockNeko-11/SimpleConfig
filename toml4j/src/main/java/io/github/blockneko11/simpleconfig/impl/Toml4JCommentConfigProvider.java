package io.github.blockneko11.simpleconfig.impl;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import io.github.blockneko11.simpleconfig.api.provider.CommentConfigProvider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Toml4JCommentConfigProvider extends Toml4JConfigProvider implements CommentConfigProvider {
    public Toml4JCommentConfigProvider() {
        super();
    }

    public Toml4JCommentConfigProvider(Toml toml, TomlWriter writer) {
        super(toml, writer);
    }

    @Override
    public String serialize(Map<String, Object> config) {
        return this.serialize(config, Collections.emptyMap());
    }

    @Override
    public String serialize(Map<String, Object> config, Map<String, List<String>> comments) {
        StringBuilder root = new StringBuilder();
        for (Map.Entry<String, Object> entry : config.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            List<String> lines = comments.get(key);
            boolean hasAnyComments = (lines != null);
            if (hasAnyComments) {
                root.append("\n");

                for (String line : lines) {
                    root.append("# ").append(line).append("\n");
                }
            }

            Map<String, Object> map = Collections.singletonMap(key, value);
            root.append(writer.write(map));
            if (hasAnyComments) {
                root.append("\n");
            }
        }

        return root.toString();
    }
}
