package io.github.blockneko11.simpleconfig.impl.holder.base;

import io.github.blockneko11.simpleconfig.api.holder.base.BooleanConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.ConfigHolderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BooleanConfigHolderImpl extends ConfigHolderImpl<Boolean> implements BooleanConfigHolder {
    protected BooleanConfigHolderImpl(@NotNull Supplier<Boolean> defaults) {
        super(Boolean.class, defaults);
    }

    @Override
    public void set(@Nullable Boolean value) {
        if (value == null) {
            super.set(false);
        }

        super.set(value);
    }

    public static class Builder
            extends BuilderImpl<Boolean, BooleanConfigHolder, BooleanConfigHolder.Builder>
            implements BooleanConfigHolder.Builder {
        public Builder() {
            super(() -> false);
        }

        @NotNull
        @Override
        public BooleanConfigHolder build() {
            return new BooleanConfigHolderImpl(defaults);
        }
    }
}
