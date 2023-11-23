package ru.hzerr.collections.list;

import ru.hzerr.collections.functions.list.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 * @author HZERR
 * @see Collections.SynchronizedList
 * @param <E> element type of collection
 */
public class SynchronizedList<E> extends ExtendedList<E> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final transient Object mutex;

    public SynchronizedList() {
        super();
        this.mutex = this;
    }

    public SynchronizedList(Object mutex) {
        super();
        this.mutex = mutex;
    }

    public SynchronizedList(int initialCapacity) {
        super(initialCapacity);
        this.mutex = this;
    }

    public SynchronizedList(int initialCapacity, Object mutex) {
        super(initialCapacity);
        this.mutex = mutex;
    }

    public SynchronizedList(Collection<? extends E> collection) {
        super(collection);
        this.mutex = this;
    }

    public SynchronizedList(Collection<? extends E> collection, Object mutex) {
        super(collection);
        this.mutex = mutex;
    }

    private SynchronizedList(SynchronizedList<? extends E> list, int from, int to) {
        this.mutex = list.mutex;
        for (int i = from; i < to; i++) {
            add(list.get(i));
        }
    }

    @Override
    public <R> HList<R> map(Function<? super E, ? extends R> mapper) {
        synchronized (mutex) {
            HList<R> list = new SynchronizedList<>();
            for (E element : this) {
                list.add(mapper.apply(element));
            }

            return list;
        }
    }

    @Override
    public <R, TH extends Exception> HList<R> map(ProtectedFunction<? super E, ? extends R, TH> mapper, Class<TH> exception) throws TH {
        synchronized (mutex) {
            HList<R> list = new SynchronizedList<>();
            for (E element : this) {
                list.add(mapper.apply(element));
            }

            return list;
        }
    }

    @Override
    public <R, TH extends Exception, TH2 extends Exception> HList<R> map(ProtectedBiFunction<? super E, ? extends R, TH, TH2> mapper, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            HList<R> list = new SynchronizedList<>();
            for (E element : this) {
                list.add(mapper.apply(element));
            }

            return list;
        }
    }

    @Override
    public <R, TH extends Exception, TH2 extends Exception, TH3 extends Exception> HList<R> map(ProtectedTHFunction<? super E, ? extends R, TH, TH2, TH3> mapper, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            HList<R> list = new SynchronizedList<>();
            for (E element : this) {
                list.add(mapper.apply(element));
            }

            return list;
        }
    }

    @Override
    public boolean allMatch(Predicate<? super E> predicate) {
        synchronized (mutex) {
            for (E element : this) {
                if (!predicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public <TH extends Exception> boolean allMatch(ProtectedPredicate<? super E, TH> predicate, Class<TH> exception) throws TH {
        synchronized (mutex) {
            for (E element : this) {
                if (!predicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean allMatch(ProtectedBiPredicate<? super E, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            for (E element : this) {
                if (!predicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean allMatch(ProtectedTHPredicate<? super E, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            for (E element : this) {
                if (!predicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public boolean anyMatch(Predicate<? super E> predicate) {
        synchronized (mutex) {
            for (E element : this) {
                if (predicate.test(element)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public <TH extends Exception> boolean anyMatch(ProtectedPredicate<? super E, TH> protectedPredicate, Class<TH> exception) throws TH {
        synchronized (mutex) {
            for (E element : this) {
                if (protectedPredicate.test(element)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean anyMatch(ProtectedBiPredicate<? super E, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            for (E element : this) {
                if (predicate.test(element)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean anyMatch(ProtectedTHPredicate<? super E, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            for (E element : this) {
                if (predicate.test(element)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public boolean noneMatch(Predicate<? super E> predicate) {
        synchronized (mutex) {
            for (E element : this) {
                if (predicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public <TH extends Exception> boolean noneMatch(ProtectedPredicate<? super E, TH> protectedPredicate, Class<TH> exception) throws TH {
        synchronized (mutex) {
            for (E element : this) {
                if (protectedPredicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean noneMatch(ProtectedBiPredicate<? super E, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            for (E element : this) {
                if (predicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean noneMatch(ProtectedTHPredicate<? super E, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            for (E element : this) {
                if (predicate.test(element)) {
                    return false;
                }
            }

            return true;
        }
    }

    @Override
    public <TH extends Exception> void forEach(ProtectedConsumer<? super E, TH> action, Class<TH> exception) throws TH {
        synchronized (mutex) {
            for (E e : this) {
                action.accept(e);
            }
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEach(ProtectedBiConsumer<? super E, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            for (E e : this) {
                action.accept(e);
            }
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEach(ProtectedTHConsumer<? super E, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            for (E e : this) {
                action.accept(e);
            }
        }
    }

    @Override
    public <TH extends Exception> boolean removeIf(ProtectedPredicate<? super E, TH> filter, Class<TH> exception) throws TH {
        boolean removed = false;
        synchronized (mutex) {
            final Iterator<E> each = iterator();
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                    each.remove();
                    removed = true;
                }
            }
            return removed;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean removeIf(ProtectedBiPredicate<? super E, TH, TH2> filter, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        boolean removed = false;
        synchronized (mutex) {
            final Iterator<E> each = iterator();
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                    each.remove();
                    removed = true;
                }
            }
            return removed;
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean removeIf(ProtectedTHPredicate<? super E, TH, TH2, TH3> filter, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        boolean removed = false;
        synchronized (mutex) {
            final Iterator<E> each = iterator();
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                    each.remove();
                    removed = true;
                }
            }
            return removed;
        }
    }

    @Override
    public void changeIf(Predicate<? super E> condition, Consumer<? super E> changer) {
        synchronized (mutex) {
            for (E element : this) {
                if (condition.test(element)) {
                    changer.accept(element);
                }
            }
        }
    }

    @Override
    public void replaceIf(Predicate<? super E> condition, E replacement) {
        synchronized (mutex) {
            for (int i = 0; i < size(); i++) {
                E element = get(i);
                if (condition.test(element)) {
                    set(i, replacement);
                }
            }
        }
    }

    @Override
    public void replaceIf(Predicate<? super E> condition, UnaryOperator<E> replacer) {
        synchronized(mutex) {
            for (int i = 0; i < size(); i++) {
                E element = get(i);
                if (condition.test(element)) {
                    set(i, replacer.apply(element));
                }
            }
        }
    }

    @Override
    public Optional<E> find(Predicate<? super E> predicate) {
        synchronized(mutex) {
            for (E element : this) {
                if (predicate.test(element)) return Optional.of(element);
            }

            return Optional.empty();
        }
    }

    @Override
    public HList<E> findAll(Predicate<? super E> predicate) {
        HList<E> values = new SynchronizedList<>();
        synchronized(mutex) {
            for (E element : this) {
                if (predicate.test(element)) values.add(element);
            }
        }

        return values;
    }

    @Override
    public boolean noContains(E element) {
        synchronized(mutex) {
            return !contains(element);
        }
    }

    @Override
    public boolean noContains(Predicate<? super E> action) {
        synchronized(mutex) {
            return !contains(action);
        }
    }

    @Override
    public boolean contains(Predicate<? super E> predicate) {
        synchronized(mutex) {
            for (E element : this) {
                if (predicate.test(element)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public <C extends Collection<E>> C to(Supplier<C> collectionFactory) {
        C collection = collectionFactory.get();
        synchronized (mutex) {
            collection.addAll(this);
        }

        return collection;
    }

    @Override
    public E firstElement() {
        synchronized (mutex) {
            return get(0);
        }
    }

    @Override
    public E lastElement() {
        synchronized (mutex) {
            return get(size() - 1);
        }
    }

    @Override
    public HList<E> subList(Predicate<E> condition) {
        HList<E> list = new ConcurrentList<>();
        synchronized (mutex) {
            for (E element : this) {
                if (condition.test(element)) {
                    list.add(element);
                }
            }
        }

        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        synchronized (mutex) {
            if (o instanceof HList) {
                //noinspection unchecked
                HList<E> target = (HList<E>) o;
                return new HashSet<>(this).containsAll(target) && new HashSet<>(target).containsAll(this);
            }

            return false;
        }
    }

    @Override
    public void trimToSize() {
        synchronized (mutex) {
            super.trimToSize();
        }
    }

    @Override
    public void ensureCapacity(int minCapacity) {
        synchronized (mutex) {
            super.ensureCapacity(minCapacity);
        }
    }

    @Override
    public int size() {
        synchronized (mutex) {
            return super.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (mutex) {
            return super.isEmpty();
        }
    }

    @Override
    public boolean contains(Object o) {
        synchronized (mutex) {
            return indexOf(o) >= 0;
        }
    }

    @Override
    public int indexOf(Object o) {
        synchronized (mutex) {
            return super.indexOf(o);
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        synchronized (mutex) {
            return super.lastIndexOf(o);
        }
    }

    @Override
    public Object clone() {
        synchronized (mutex) {
            return super.clone();
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        synchronized (mutex) {
            return super.toArray(a);
        }
    }

    @Override
    public Object[] toArray() {
        synchronized (mutex) {
            return super.toArray();
        }
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return toArray(generator.apply(0));
    }

    @Override
    public E[] toArray(Class<E> clazz) {
        return toArray((E[]) Array.newInstance(clazz, size()));
    }

    @Override
    public E get(int index) {
        synchronized (mutex) {
            return super.get(index);
        }
    }

    @Override
    public E set(int index, E element) {
        synchronized (mutex) {
            return super.set(index, element);
        }
    }

    @Override
    public boolean add(E e) {
        synchronized (mutex) {
            return super.add(e);
        }
    }

    @Override
    public void add(int index, E element) {
        synchronized (mutex) {
            super.add(index, element);
        }
    }

    @Override
    public E remove(int index) {
        synchronized (mutex) {
            return super.remove(index);
        }
    }

    @Override
    public boolean remove(Object o) {
        synchronized (mutex) {
            return super.remove(o);
        }
    }

    @Override
    public void clear() {
        synchronized (mutex) {
            super.clear();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        synchronized (mutex) {
            return super.addAll(c);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        synchronized (mutex) {
            return super.addAll(index, c);
        }
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        synchronized (mutex) {
            super.removeRange(fromIndex, toIndex);
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        synchronized (mutex) {
            return super.removeAll(c);
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        synchronized (mutex) {
            return super.retainAll(c);
        }
    }

    @Override
    @SyncByUser
    public ListIterator<E> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    @SyncByUser
    public ListIterator<E> listIterator() {
        return super.listIterator();
    }

    @Override
    @SyncByUser
    public Iterator<E> iterator() {
        return super.iterator();
    }

    @Override
    public HList<E> subList(int fromIndex, int toIndex) {
        synchronized (mutex) {
            return new SynchronizedList<>(this, fromIndex, toIndex);
        }
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        synchronized (this) {
            super.forEach(action);
        }
    }

    @Override
    @SyncByUser
    public Spliterator<E> spliterator() {
        return super.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        synchronized (mutex) {
            return super.removeIf(filter);
        }
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        synchronized (mutex) {
            super.replaceAll(operator);
        }
    }

    @Override
    public void sort(Comparator<? super E> c) {
        synchronized (mutex) {
            super.sort(c);
        }
    }

    @Override
    public int hashCode() {
        synchronized (mutex) {
            return super.hashCode();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        synchronized (mutex) {
            return super.containsAll(c);
        }
    }

    @Override
    public String toString() {
        synchronized (mutex) {
            return super.toString();
        }
    }

    @Override
    @SyncByUser
    public Stream<E> stream() {
        return super.stream();
    }

    @Override
    @SyncByUser
    public Stream<E> parallelStream() {
        return super.parallelStream();
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        synchronized (mutex) {
            oos.defaultWriteObject();
        }
    }
}
