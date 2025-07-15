package io.github.blockneko11.simpleconfig.impl.provider;

import com.typesafe.config.ConfigFactory;
import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;

import java.util.Map;
import java.util.stream.Collectors;

public class TCConfigProvider implements ConfigProvider {
    @Override
    public Map<String, Object> parse(String config) {
        return ConfigFactory.parseString(config)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().unwrapped()
                )
        );
    }

    @Override
    public String serialize(Map<String, Object> config) {
        return ConfigFactory.parseMap(config)
                .root()
                .render(TCOptions.RENDER_OPTIONS.get());
    }
}
