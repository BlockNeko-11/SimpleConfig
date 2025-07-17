package io.github.blockneko11.simpleconfig.api.adapter.serialize;

import java.util.Map;

public interface ConfigValueSerializer<T> {
    Map<String, Object> serialize(T value);
}
