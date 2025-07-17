package io.github.blockneko11.simpleconfig.impl.provider;

import io.github.blockneko11.simpleconfig.api.provider.CommentConfigProvider;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SnakeYamlCommentConfigProvider extends SnakeYamlConfigProvider implements CommentConfigProvider {
    public SnakeYamlCommentConfigProvider() {
        super();
    }

    public SnakeYamlCommentConfigProvider(Yaml yaml) {
        super(yaml);
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

            MappingNode node = toNode(Collections.singletonMap(key, value));
            List<String> lines = comments.get(key);
            boolean hasAnyComments = (lines != null);

            try (StringWriter sw = new StringWriter();
                 BufferedWriter bw = new BufferedWriter(sw)) {
                this.yaml.serialize(node, bw);
                bw.flush();

                if (hasAnyComments) {
                    sb.append("\n");

                    for (String line : lines) {
                        sb.append("# ").append(line).append("\n");
                    }
                }
                sb.append(sw);

                if (hasAnyComments) {
                    sb.append("\n");
                }
            } catch (IOException ignored) {
            }
        }

        return sb.toString();
    }

    private MappingNode toNode(Map<String, Object> map) {
        List<NodeTuple> tuples = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Node nodeKey = this.yaml.represent(key);
            Node nodeValue;

            if (value instanceof Map) {
                nodeValue = this.toNode((Map<String, Object>) value);
            } else {
                nodeValue = this.yaml.represent(value);
            }

            tuples.add(new NodeTuple(nodeKey, nodeValue));
        }

        return new MappingNode(Tag.MAP, tuples, DumperOptions.FlowStyle.BLOCK);
    }
}
