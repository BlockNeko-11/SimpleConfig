package io.github.blockneko11.simpleconfig.api.holder.lang;

import io.github.blockneko11.simpleconfig.api.holder.base.ObjectConfigHolder;

public interface EnumConfigHolder<E extends Enum<E>> extends ObjectConfigHolder<E> {
    interface Builder<E extends Enum<E>> extends ObjectConfigHolder.Builder<E> {
    }
}
