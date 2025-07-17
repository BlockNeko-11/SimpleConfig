package io.github.blockneko11.simpleconfig.api.holder.base;

import io.github.blockneko11.simpleconfig.api.adapter.ConfigValueAdapter;
import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.base.ObjectConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

public interface ObjectConfigHolder<T> extends ConfigHolder<T> {
    static <T> ObjectConfigHolder.Builder<T> builder(@NotNull Class<T> clazz) {
        return new ObjectConfigHolderImpl.Builder<>(clazz);
    }

    @NotNull
    ConfigValueParser<T> getParser();

    @NotNull
    ConfigValueSerializer<T> getSerializer();

    interface Builder<T> extends ConfigHolder.Builder<T, ObjectConfigHolder<T>, Builder<T>> {
        default Builder<T> adapter(@NotNull ConfigValueAdapter<T> adapter) {
            return this.parser(adapter).serializer(adapter);
        }

        Builder<T> parser(@NotNull ConfigValueParser<T> parser);

        Builder<T> serializer(@NotNull ConfigValueSerializer<T> serializer);
    }
}
