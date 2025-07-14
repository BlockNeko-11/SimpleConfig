package io.github.blockneko11.simpleconfig.api.holder.collection;

import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.collection.MapConfigHolderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 表示一个 {@link Map} 类型的配置项，同时也实现了 {@link Map} 接口。
 * @param <K> {@link Map} 中键的类型
 * @param <V> {@link Map} 中值的类型
 * @see ConfigHolder
 * @see Map
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface MapConfigHolder<K, V> extends ConfigHolder<Map<K, V>>, Map<K, V> {
    /**
     * 创建一个 {@link Map} 类型的配置项的构建器。
     * @param keyClass {@link Map} 中键的类型
     * @param valueClass {@link Map} 中值的类型
     * @return 构建器
     * @param <K> {@link Map} 中键的类型
     * @param <V> {@link Map} 中值的类型
     */
    static <K, V> Builder<K, V> builder(Class<K> keyClass, Class<V> valueClass) {
        return new MapConfigHolderImpl.Builder<>(keyClass, valueClass);
    }

    @Override
    default int size() {
        return get().size();
    }

    @Override
    default boolean isEmpty() {
        return get().isEmpty();
    }

    @Override
    default boolean containsKey(Object key) {
        return get().containsKey(key);
    }

    @Override
    default boolean containsValue(Object value) {
        return get().containsValue(value);
    }

    @Override
    default V get(Object key) {
        return get().get(key);
    }

    @Nullable
    @Override
    default V put(K key, V value) {
        return get().put(key, value);
    }

    @Override
    default V remove(Object key) {
        return get().remove(key);
    }

    @Override
    default void putAll(@NotNull Map<? extends K, ? extends V> m) {
        get().putAll(m);
    }

    @Override
    default void clear() {
        get().clear();
    }

    @NotNull
    @Override
    default Set<K> keySet() {
        return get().keySet();
    }

    @NotNull
    @Override
    default Collection<V> values() {
        return get().values();
    }

    @NotNull
    @Override
    default Set<Entry<K, V>> entrySet() {
        return get().entrySet();
    }

    /**
     * 表示一个创建 {@link Map} 类型的配置项的构建器。
     * @param <K> {@link Map} 中键的类型
     * @param <V> {@link Map} 中值的类型
     * @see ConfigHolder.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder<K, V> extends ConfigHolder.Builder<Map<K, V>> {
        @Override
        default Builder<K, V> defaults(@Nullable Map<K, V> defaults) {
            return (Builder<K, V>) ConfigHolder.Builder.super.defaults(defaults);
        }

        @Override
        Builder<K, V> defaults(@NotNull Supplier<Map<K, V>> defaults);

        @Override
        MapConfigHolder<K, V> build();
    }
}
