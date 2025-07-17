package io.github.blockneko11.simpleconfig.api.adapter.serialize;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface ConfigValueSerializer<T> {
    @Nullable
    Object serialize(T value);
}
