package io.github.blockneko11.simpleconfig.impl.holder.number;

import io.github.blockneko11.simpleconfig.api.holder.number.NumberConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.NumberConfigHolderConstructor;
import io.github.blockneko11.simpleconfig.impl.holder.AbstractBuilder;
import io.github.blockneko11.simpleconfig.impl.holder.ConfigHolderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public abstract class NumberConfigHolderImpl<N extends Number> extends ConfigHolderImpl<N> implements NumberConfigHolder<N> {
    private final N min;
    private final N max;

    protected NumberConfigHolderImpl(@NotNull Class<N> clazz,
                                     @NotNull Supplier<N> defaults,
                                     @NotNull N min,
                                     @NotNull N max) {
        super(clazz, defaults);
        this.min = min;
        this.max = max;
    }

    @NotNull
    @Override
    public N getMin() {
        return this.min;
    }

    @NotNull
    @Override
    public N getMax() {
        return this.max;
    }

    @NotNull
    @Override
    public N getDefaults() {
        N defaults = super.getDefaults();

        if (defaults == null) {
            throw new IllegalArgumentException("defaults cannot be null");
        }

        if (!this.isInRange(defaults)) {
            throw new IllegalArgumentException("defaults is out of range");
        }

        return defaults;
    }

    @Override
    public void set(@Nullable N value) {
        N defaults = this.getDefaults();

        if (value == null) {
            super.set(defaults);
            return;
        }

        if (!this.isInRange(value)) {
            super.set(defaults);
            return;
        }

        super.set(value);
    }

    public static abstract class Builder<N extends Number> extends AbstractBuilder<N> implements NumberConfigHolder.Builder<N> {
        private final NumberConfigHolderConstructor<N> constructor;
        protected N min;
        protected N max;

        protected Builder(NumberConfigHolderConstructor<N> constructor,
                          @NotNull N min,
                          @NotNull N max,
                          @NotNull Supplier<N> defaults) {
            super(defaults);
            this.constructor = constructor;
            this.min = min;
            this.max = max;
        }

        @Override
        public Builder<N> defaults(N defaults) {
            return (Builder<N>) super.defaults(defaults);
        }

        @Override
        public Builder<N> defaults(@NotNull Supplier<N> defaults) {
            return (Builder<N>) super.defaults(defaults);
        }

        @Override
        public Builder<N> min(@NotNull N min) {
            this.min = min;
            return this;
        }

        @Override
        public Builder<N> max(@NotNull N max) {
            this.max = max;
            return this;
        }

        @Override
        public NumberConfigHolder<N> build() {
            return this.constructor.create(defaults, this.min, this.max);
        }
    }
}
