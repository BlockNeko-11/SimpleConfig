package io.github.blockneko11.simpleconfig.impl.holder;

import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class AbstractBuilder<T> implements ConfigHolder.Builder<T> {
    protected Supplier<T> defaults;

    protected AbstractBuilder(@NotNull Supplier<T> defaults) {
        this.defaults = defaults;
    }

    @Override
    public AbstractBuilder<T> defaults(T defaults) {
        return this.defaults(() -> defaults);
    }

    @Override
    public AbstractBuilder<T> defaults(@NotNull Supplier<T> defaults) {
        this.defaults = defaults;
        return this;
    }
}
