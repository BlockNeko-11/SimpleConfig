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

    public static class Builder
            extends NumberConfigHolderImpl.Builder<Double, DoubleConfigHolder, DoubleConfigHolder.Builder>
            implements DoubleConfigHolder.Builder {
        public Builder() {
            super(DoubleConfigHolderImpl::new, Double.MIN_VALUE, Double.MAX_VALUE, () -> 0.0D);
        }
    }
}
