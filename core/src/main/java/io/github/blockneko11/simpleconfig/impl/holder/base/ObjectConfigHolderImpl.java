package io.github.blockneko11.simpleconfig.impl.holder.base;

import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.holder.base.ObjectConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.ConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ObjectConfigHolderImpl<T>
        extends ConfigHolderImpl<T>
        implements ObjectConfigHolder<T> {

    private final ConfigValueParser<T> parser;
    private final ConfigValueSerializer<T> serializer;

    protected ObjectConfigHolderImpl(@NotNull Class<T> clazz,
                                     @NotNull Supplier<T> defaults,
                                     @NotNull ConfigValueParser<T> parser,
                                     @NotNull ConfigValueSerializer<T> serializer) {
        super(clazz, defaults);
        this.parser = parser;
        this.serializer = serializer;
    }

    @NotNull
    @Override
    public ConfigValueParser<T> getParser() {
        return this.parser;
    }

    @NotNull
    @Override
    public ConfigValueSerializer<T> getSerializer() {
        return this.serializer;
    }

    public static class Builder<T>
            extends ConfigHolderImpl.BuilderImpl<T, ObjectConfigHolder<T>, ObjectConfigHolder.Builder<T>>
            implements ObjectConfigHolder.Builder<T> {

        private final Class<T> clazz;
        private ConfigValueParser<T> parser;
        private ConfigValueSerializer<T> serializer;

        public Builder(@NotNull Class<T> clazz) {
            super(() -> null);
            this.clazz = clazz;
        }

        @Override
        public ObjectConfigHolder.Builder<T> parser(@NotNull ConfigValueParser<T> parser) {
            this.parser = parser;
            return this;
        }

        @Override
        public ObjectConfigHolder.Builder<T> serializer(@NotNull ConfigValueSerializer<T> serializer) {
            this.serializer = serializer;
            return this;
        }

        @Override
        public ObjectConfigHolder<T> build() {
            return new ObjectConfigHolderImpl<>(this.clazz, super.defaults, this.parser, this.serializer);
        }
    }
}
