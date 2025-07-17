package io.github.blockneko11.simpleconfig.api.adapter.parse;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ConfigValueParser<T> {
    @NotNull
    T parse(@NotNull Object value);
}
