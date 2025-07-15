package io.github.blockneko11.simpleconfig.impl.provider;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import io.github.blockneko11.simpleconfig.api.provider.ConfigProvider;

import java.util.Map;
import java.util.stream.Collectors;

public class TCConfigProvider implements ConfigProvider {
    @Override
    public Map<String, Object> parse(String config) {
        Config tcConfig = ConfigFactory.parseString(config);

        return tcConfig.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().unwrapped()
                )
        );
    }

    @Override
    public String serialize(Map<String, Object> config) {
        ConfigRenderOptions options = ConfigRenderOptions.defaults()
                .setJson(false)
                .setOriginComments(false);

        return ConfigFactory.parseMap(config)
                .root()
                .render(options);
    }
}
