package io.github.blockneko11.simpleconfig.impl.provider;

import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.Map;

public class SnakeYamlConfigProvider implements ConfigProvider {
    private final Yaml yaml;

    public SnakeYamlConfigProvider() {
        this(new Yaml());
    }

    public SnakeYamlConfigProvider(Yaml yaml) {
        this.yaml = yaml;
    }

    @Override
    public Map<String, Object> parse(String config) {
        return this.yaml.load(config);
    }

    @Override
    public String serialize(Map<String, Object> config) {
        return this.yaml.dumpAs(config, Tag.MAP, null);
    }
}
