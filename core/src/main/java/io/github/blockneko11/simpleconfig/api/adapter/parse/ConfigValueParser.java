package io.github.blockneko11.simpleconfig.api.adapter.parse;

import java.util.Map;

public interface ConfigValueParser<T> {
    T parse(Map<String, Object> config);
}
