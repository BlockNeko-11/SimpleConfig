package io.github.blockneko11.simpleconfig.impl;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;

import java.util.Map;

public class Toml4JConfigProvider implements ConfigProvider {
    private final Toml toml;
    private final TomlWriter writer;

    public Toml4JConfigProvider() {
        this(new Toml(), new TomlWriter());
    }

    public Toml4JConfigProvider(Toml toml, TomlWriter writer) {
        this.toml = toml;
        this.writer = writer;
    }

    @Override
    public Map<String, Object> parse(String config) {
        return this.toml.read(config).toMap();
    }

    @Override
    public String serialize(Map<String, Object> config) {
        return this.writer.write(config);
    }
}
