package io.github.blockneko11.simpleconfig.impl.holder.collection;

import io.github.blockneko11.simpleconfig.api.holder.collection.ListConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.ConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

public class ListConfigHolderImpl<E> extends ConfigHolderImpl<List<E>> implements ListConfigHolder<E> {
    private final Class<E> elementClass;

    protected ListConfigHolderImpl(Class<E> elementClass, @NotNull Supplier<List<E>> defaults) {
        super((Class<List<E>>) (Class<?>) List.class, defaults);
        this.elementClass = elementClass;
    }

    public static class Builder<E>
            extends BuilderImpl<List<E>, ListConfigHolder<E>, ListConfigHolder.Builder<E>>
            implements ListConfigHolder.Builder<E> {

        private final Class<E> elementClass;

        public Builder(@NotNull Class<E> elementClass) {
            super(ArrayList::new);
            this.elementClass = elementClass;
        }

        @Override
        public ListConfigHolder<E> build() {
            return new ListConfigHolderImpl<>(this.elementClass, defaults);
        }
    }
}
