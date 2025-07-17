package io.github.blockneko11.simpleconfig.api.holder.collection;

import io.github.blockneko11.simpleconfig.api.holder.ConfigHolder;
import io.github.blockneko11.simpleconfig.impl.holder.collection.ListConfigHolderImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 表示一个 {@link List} 类型的配置项，同时也实现了 {@link List} 接口。
 * @param <E> {@link List} 中元素的类型
 * @see ConfigHolder
 * @see List
 * @author BlockNeko-11
 * @since 1.0.0
 */
public interface ListConfigHolder<E> extends ConfigHolder<List<E>>, List<E> {
    /**
     * 创建一个 {@link List} 类型的配置项的构建器。
     * @param elementClass {@link List} 中元素的类型
     * @return 构建器
     * @param <E> {@link List} 中元素的类型
     */
    static <E> ListConfigHolder.Builder<E> builder(Class<E> elementClass) {
        return new ListConfigHolderImpl.Builder<>(elementClass);
    }

    @Override
    default int size() {
        return this.get().size();
    }

    @Override
    default boolean isEmpty() {
        return this.get().isEmpty();
    }

    @Override
    default boolean contains(Object o) {
        return this.get().contains(o);
    }

    @NotNull
    @Override
    default Iterator<E> iterator() {
        return this.get().iterator();
    }

    @NotNull
    @Override
    default Object[] toArray() {
        return this.get().toArray();
    }

    @NotNull
    @Override
    default <T> T[] toArray(@NotNull T[] a) {
        return this.get().toArray(a);
    }

    @Override
    default boolean add(E e) {
        return this.get().add(e);
    }

    @Override
    default boolean remove(Object o) {
        return this.get().remove(o);
    }

    @Override
    default boolean containsAll(@NotNull Collection<?> c) {
        return this.get().containsAll(c);
    }

    @Override
    default boolean addAll(@NotNull Collection<? extends E> c) {
        return this.get().addAll(c);
    }

    @Override
    default boolean addAll(int index, @NotNull Collection<? extends E> c) {
        return this.get().addAll(index, c);
    }

    @Override
    default boolean removeAll(@NotNull Collection<?> c) {
        return this.get().removeAll(c);
    }

    @Override
    default boolean retainAll(@NotNull Collection<?> c) {
        return this.get().retainAll(c);
    }

    @Override
    default void clear() {
        this.get().clear();
    }

    @Override
    default E get(int index) {
        return this.get().get(index);
    }

    @Override
    default E set(int index, E element) {
        return this.get().set(index, element);
    }

    @Override
    default void add(int index, E element) {
        this.get().add(index, element);
    }

    @Override
    default E remove(int index) {
        return this.get().remove(index);
    }

    @Override
    default int indexOf(Object o) {
        return this.get().indexOf(o);
    }

    @Override
    default int lastIndexOf(Object o) {
        return this.get().lastIndexOf(o);
    }

    @NotNull
    @Override
    default ListIterator<E> listIterator() {
        return this.get().listIterator();
    }

    @NotNull
    @Override
    default ListIterator<E> listIterator(int index) {
        return this.get().listIterator(index);
    }

    @NotNull
    @Override
    default List<E> subList(int fromIndex, int toIndex) {
        return this.get().subList(fromIndex, toIndex);
    }

    /**
     * 表示一个创建 {@link List} 类型的配置项的构建器。
     * @param <E> {@link List} 中元素的类型
     * @see ConfigHolder.Builder
     * @author BlockNeko-11
     * @since 1.0.0
     */
    interface Builder<E> extends ConfigHolder.Builder<List<E>, ListConfigHolder<E>, Builder<E>> {
    }
}
