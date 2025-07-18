package io.github.blockneko11.simpleconfig.impl.holder.number;

import io.github.blockneko11.simpleconfig.api.holder.number.NumberConfigHolder;
import io.github.blockneko11.simpleconfig.api.holder.number.NumberConfigHolderConstructor;
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
        N def = super.getDefaults();

        if (!this.isInRange(def)) {
            throw new IllegalArgumentException("defaults is out of range");
        }

        return def;
    }

    @Override
    public void set(@Nullable N value) {
        N defaults = this.getDefaults();

        if (value == null || !this.isInRange(value)) {
            super.set(defaults);
            return;
        }

        super.set(value);
    }

    public static abstract class Builder<
            N extends Number,
            Result extends NumberConfigHolder<N>,
            Impl extends NumberConfigHolder.Builder<N, Result, Impl>
            >
            extends BuilderImpl<N, Result, Impl>
            implements NumberConfigHolder.Builder<N, Result, Impl> {

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
        public Impl min(@NotNull N min) {
            this.min = min;
            return (Impl) this;
        }

        @Override
        public Impl max(@NotNull N max) {
            this.max = max;
            return (Impl) this;
        }

        @Override
        public Result build() {
            return (Result) this.constructor.create(defaults, this.min, this.max);
        }
    }
}
