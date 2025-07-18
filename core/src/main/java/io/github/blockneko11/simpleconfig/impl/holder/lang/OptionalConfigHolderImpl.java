package io.github.blockneko11.simpleconfig.impl.holder.lang;

import io.github.blockneko11.simpleconfig.api.adapter.parse.ConfigValueParser;
import io.github.blockneko11.simpleconfig.api.adapter.serialize.ConfigValueSerializer;
import io.github.blockneko11.simpleconfig.api.holder.lang.OptionalConfigHolder;
import io.github.blockneko11.simpleconfig.impl.adapter.lang.OptionalAdapter;
import io.github.blockneko11.simpleconfig.impl.holder.base.ObjectConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalConfigHolderImpl<T>
        extends ObjectConfigHolderImpl<Optional<T>>
        implements OptionalConfigHolder<T> {
    protected OptionalConfigHolderImpl(@NotNull Class<Optional<T>> clazz,
                                       @NotNull Supplier<Optional<T>> defaults) {
        super(clazz, defaults, new OptionalAdapter<>());
    }

    public static class Builder<T>
            extends ObjectConfigHolderImpl.Builder<Optional<T>>
            implements OptionalConfigHolder.Builder<T> {
        public Builder() {
            super((Class<Optional<T>>) (Class<?>) Optional.class, Optional::empty);
        }

        @Override
        public Builder<T> parser(@NotNull ConfigValueParser<Optional<T>> parser) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Builder<T> serializer(@NotNull ConfigValueSerializer<Optional<T>> serializer) {
            throw new UnsupportedOperationException();
        }

        @Override
        public OptionalConfigHolder<T> build() {
            return new OptionalConfigHolderImpl<>(super.clazz, super.defaults);
        }
    }
}
