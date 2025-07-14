package io.github.blockneko11.simpleconfig.impl.holder.base;

import io.github.blockneko11.simpleconfig.api.holder.base.BooleanConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.AbstractBuilder;
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

    public static class Builder extends AbstractBuilder<Boolean> implements BooleanConfigHolder.Builder {
        public Builder() {
            super(() -> false);
        }

        @Override
        public Builder defaults(Boolean defaults) {
            return (Builder) super.defaults(defaults);
        }

        @Override
        public Builder defaults(@NotNull Supplier<Boolean> defaults) {
            return (Builder) super.defaults(defaults);
        }

        @NotNull
        @Override
        public BooleanConfigHolder build() {
            return new BooleanConfigHolderImpl(defaults);
        }
    }
}
