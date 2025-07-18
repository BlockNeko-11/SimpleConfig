package io.github.blockneko11.simpleconfig.impl.adapter.lang;

import io.github.blockneko11.simpleconfig.api.adapter.ConfigValueAdapter;
import org.jetbrains.annotations.NotNull;

public class EnumAdapter<E extends Enum<E>> implements ConfigValueAdapter<E> {
    private final Class<E> clazz;

    public EnumAdapter(@NotNull Class<E> clazz) {
        this.clazz = clazz;
    }

    @NotNull
    @Override
    public E parse(@NotNull Object value) {
        return Enum.valueOf(this.clazz, (String) value);
    }

    @NotNull
    @Override
    public Object serialize(E value) {
        return value.name();
    }
}
