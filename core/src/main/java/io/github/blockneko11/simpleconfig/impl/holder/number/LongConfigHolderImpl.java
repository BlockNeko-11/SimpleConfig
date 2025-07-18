package io.github.blockneko11.simpleconfig.impl.holder.number;

import io.github.blockneko11.simpleconfig.api.holder.number.LongConfigHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LongConfigHolderImpl extends NumberConfigHolderImpl<Long> implements LongConfigHolder {
    protected LongConfigHolderImpl(@NotNull Supplier<Long> defaults, @NotNull Long min, @NotNull Long max) {
        super(Long.class, defaults, min, max);
    }

    @Override
    public boolean isInRange(@NotNull Long value) {
        return value >= this.getMin() && value <= this.getMax();
    }

    public static class Builder
            extends NumberConfigHolderImpl.Builder<Long, LongConfigHolder, LongConfigHolder.Builder>
            implements LongConfigHolder.Builder {
        public Builder() {
            super(LongConfigHolderImpl::new, Long.MIN_VALUE, Long.MAX_VALUE, () -> 0L);
        }
    }
}
