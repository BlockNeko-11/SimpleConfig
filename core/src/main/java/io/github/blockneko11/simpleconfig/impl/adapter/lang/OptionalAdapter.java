package io.github.blockneko11.simpleconfig.impl.adapter.lang;

import io.github.blockneko11.simpleconfig.api.adapter.ConfigValueAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class OptionalAdapter<T> implements ConfigValueAdapter<Optional<T>> {
    @NotNull
    @Override
    public Optional<T> parse(@NotNull Object value) {
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of((T) value);
    }

    @Nullable
    @Override
    public Object serialize(Optional<T> value) {
        return value.orElse(null);
    }
}
