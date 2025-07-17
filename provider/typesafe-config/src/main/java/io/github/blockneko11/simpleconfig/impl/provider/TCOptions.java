package io.github.blockneko11.simpleconfig.impl.provider;

import com.typesafe.config.ConfigRenderOptions;
import io.github.blockneko11.simpleconfig.util.Lazy;

public interface TCOptions {
    Lazy<ConfigRenderOptions> RENDER_OPTIONS = Lazy.of(() -> ConfigRenderOptions.defaults()
            .setJson(false)
            .setOriginComments(false)
    );
}
