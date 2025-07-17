package io.github.blockneko11.simpleconfig.api.adapter;

import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;

public interface ConfigValueAdapter<T> extends ConfigValueParser<T>, ConfigValueSerializer<T> {
}
