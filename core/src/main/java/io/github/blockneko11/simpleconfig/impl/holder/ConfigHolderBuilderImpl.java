package io.github.blockneko11.simpleconfig.impl.holder;

import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class ConfigHolderBuilderImpl<T> implements ConfigHolder.Builder<T> {
    protected Supplier<T> defaults;
    protected ConfigValueParser<T> parser;
    protected ConfigValueSerializer<T> serializer;

    protected ConfigHolderBuilderImpl(@NotNull Supplier<T> defaults) {
        this.defaults = defaults;
    }

    @Override
    public ConfigHolderBuilderImpl<T> defaults(@NotNull Supplier<T> defaults) {
        this.defaults = defaults;
        return this;
    }

    @Override
    public ConfigHolder.Builder<T> parser(@Nullable ConfigValueParser<T> parser) {
        this.parser = parser;
        return this;
    }

    @Override
    public ConfigHolder.Builder<T> serializer(@Nullable ConfigValueSerializer<T> serializer) {
        this.serializer = serializer;
        return this;
    }
}
