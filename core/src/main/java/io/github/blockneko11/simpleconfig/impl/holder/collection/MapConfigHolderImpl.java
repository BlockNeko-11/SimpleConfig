package io.github.blockneko11.simpleconfig.impl.holder.collection;

import io.github.blockneko11.simpleconfig.api.holder.collection.MapConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.AbstractBuilder;
import io.github.blockneko11.simpleconfig.impl.holder.ConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MapConfigHolderImpl<K, V> extends ConfigHolderImpl<Map<K, V>> implements MapConfigHolder<K, V> {
    private final Class<K> keyClass;
    private final Class<V> valueClass;

    protected MapConfigHolderImpl(@NotNull Class<K> keyClass,
                                  @NotNull Class<V> valueClass,
                                  @NotNull Supplier<Map<K, V>> defaults) {
        super((Class<Map<K,V>>) (Class<?>) Map.class, defaults);
        this.keyClass = keyClass;
        this.valueClass = valueClass;
    }

    public static class Builder<K, V> extends AbstractBuilder<Map<K, V>> implements MapConfigHolder.Builder<K, V> {
        private final Class<K> keyClass;
        private final Class<V> valueClass;

        public Builder(@NotNull Class<K> keyClass, @NotNull Class<V> valueClass) {
            super(LinkedHashMap::new);
            this.keyClass = keyClass;
            this.valueClass = valueClass;
        }

        @Override
        public Builder<K, V> defaults(Map<K, V> defaults) {
            return (Builder<K, V>) super.defaults(defaults);
        }

        @Override
        public Builder<K, V> defaults(@NotNull Supplier<Map<K, V>> defaults) {
            return (Builder<K, V>) super.defaults(defaults);
        }

        @Override
        public MapConfigHolder<K, V> build() {
            return new MapConfigHolderImpl<>(this.keyClass, this.valueClass, defaults);
        }
    }
}
