package io.github.blockneko11.simpleconfig.impl.holder.lang;

import io.github.blockneko11.simpleconfig.api.adapter.ConfigValueAdapter;
import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.holder.lang.EnumConfigHolder;
import io.github.blockneko11.simpleconfig.impl.adapter.lang.EnumAdapter;
import io.github.blockneko11.simpleconfig.impl.holder.base.ObjectConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EnumConfigHolderImpl<E extends Enum<E>>
        extends ObjectConfigHolderImpl<E>
        implements EnumConfigHolder<E> {

    protected EnumConfigHolderImpl(@NotNull Class<E> clazz,
                                   @NotNull Supplier<E> defaults) {
        super(clazz, defaults, new EnumAdapter<>(clazz));
    }

    public static class Builder<E extends Enum<E>>
            extends ObjectConfigHolderImpl.Builder<E>
            implements EnumConfigHolder.Builder<E> {

        public Builder(@NotNull Class<E> clazz) {
            super(clazz);
        }

        @Override
        public Builder<E> parser(@NotNull ConfigValueParser<E> parser) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Builder<E> serializer(@NotNull ConfigValueSerializer<E> serializer) {
            throw new UnsupportedOperationException();
        }

        @Override
        public EnumConfigHolder<E> build() {
            return new EnumConfigHolderImpl<>(super.clazz, super.defaults);
        }
    }
}
