package io.github.blockneko11.simpleconfig.api.holder.lang;

import io.github.blockneko11.simpleconfig.api.holder.base.ObjectConfigHolder;

import java.util.Optional;

public interface OptionalConfigHolder<T> extends ObjectConfigHolder<Optional<T>> {
    interface Builder<T> extends ObjectConfigHolder.Builder<Optional<T>> {
        @Override
        OptionalConfigHolder<T> build();
    }
}
