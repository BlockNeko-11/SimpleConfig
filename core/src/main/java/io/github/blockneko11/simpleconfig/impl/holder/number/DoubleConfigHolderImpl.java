package io.github.blockneko11.simpleconfig.impl.holder.number;

import io.github.blockneko11.simpleconfig.api.holder.number.DoubleConfigHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class DoubleConfigHolderImpl extends NumberConfigHolderImpl<Double> implements DoubleConfigHolder {
    protected DoubleConfigHolderImpl(@NotNull Supplier<Double> defaults, @NotNull Double min, @NotNull Double max) {
        super(Double.class, defaults, min, max);
    }

    @Override
    public boolean isInRange(@NotNull Double value) {
        return value >= this.getMin() && value <= this.getMax();
    }

    public static class Builder extends NumberConfigHolderImpl.Builder<Double> implements DoubleConfigHolder.Builder {
        public Builder() {
            super(DoubleConfigHolderImpl::new, Double.MIN_VALUE, Double.MAX_VALUE, () -> 0.0D);
        }

        @Override
        public Builder min(@NotNull Double min) {
            return (Builder) super.min(min);
        }

        @Override
        public Builder max(@NotNull Double max) {
            return (Builder) super.max(max);
        }

        @Override
        public Builder defaults(Double defaults) {
            return (Builder) super.defaults(defaults);
        }

        @Override
        public Builder defaults(@NotNull Supplier<Double> defaults) {
            return (Builder) super.defaults(defaults);
        }

        @NotNull
        @Override
        public DoubleConfigHolder build() {
            return (DoubleConfigHolder) super.build();
        }
    }
}
