package io.github.blockneko11.simpleconfig.impl.holder.number;

import io.github.blockneko11.simpleconfig.api.holder.number.IntegerConfigHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class IntegerConfigHolderImpl extends NumberConfigHolderImpl<Integer> implements IntegerConfigHolder {
    protected IntegerConfigHolderImpl(@NotNull Supplier<Integer> defaults, @NotNull Integer min, @NotNull Integer max) {
        super(Integer.class, defaults, min, max);
    }

    @Override
    public boolean isInRange(@NotNull Integer value) {
        return value >= this.getMin() && value <= this.getMax();
    }

    public static class Builder extends NumberConfigHolderImpl.Builder<Integer> implements IntegerConfigHolder.Builder {
        public Builder() {
            super(IntegerConfigHolderImpl::new, Integer.MIN_VALUE, Integer.MAX_VALUE, () -> 0);
        }

        @Override
        public Builder min(@NotNull Integer min) {
            return (Builder) super.min(min);
        }

        @Override
        public Builder max(@NotNull Integer max) {
            return (Builder) super.max(max);
        }

        @Override
        public Builder defaults(Integer defaults) {
            return (Builder) super.defaults(defaults);
        }

        @Override
        public Builder defaults(@NotNull Supplier<Integer> defaults) {
            return (Builder) super.defaults(defaults);
        }

        @NotNull
        @Override
        public IntegerConfigHolder build() {
            return (IntegerConfigHolder) super.build();
        }
    }
}
