package ru.hzerr.collections.list;

import ru.hzerr.collections.functions.list.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.*;
import java.util.function.IntFunction;

/**
 * @version 1.5.3
 * @param <E> the type of elements held in this collection
 */
@SuppressWarnings("unchecked")
public class ConcurrentList<E> extends CopyOnWriteArrayList<E> implements HList<E> {

    final transient ReentrantLock lock = new ReentrantLock();

    public ConcurrentList() { super(); }
    public ConcurrentList(E[] toCopyIn) { super(toCopyIn); }
    public ConcurrentList(Collection<? extends E> collection) { super(collection); }

    private ConcurrentList(HList<? extends E> collection, int from, int to) {
        for (int i = from; i < to; i++) {
            add(collection.get(i));
        }
    }

    @Override
    public void changeIf(Predicate<? super E> condition, Consumer<? super E> changer) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for (E element : this) {
                if (condition.test(element)) {
                    changer.accept(element);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean noContains(E element) {
        return !contains(element);
    }

    @Override
    public boolean noContains(Predicate<? super E> action) {
        return !contains(action);
    }

    @Override
    public boolean contains(Predicate<? super E> action) {
        for (E element : this) {
            if (action.test(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    @SafeVarargs
    public final boolean containsAll(E... elements) {
        for (E e : elements)
            if (noContains(e))
                return false;

        return true;
    }

    @Override
    @SafeVarargs
    public final boolean containsAll(Predicate<? super E>... actions) {
        for (Predicate<? super E> action : actions)
            if (noContains(action))
                return false;

        return true;
    }

    @Override
    @SafeVarargs
    public final boolean noContainsAll(E... elements) {
        for (E e : elements)
            if (contains(e))
                return false;

        return true;
    }

    @Override
    @SafeVarargs
    public final boolean noContainsAll(Predicate<? super E>... actions) {
        for (Predicate<? super E> action : actions)
            if (contains(action))
                return false;

        return true;
    }

    @Override
    public Optional<E> find(Predicate<? super E> predicate) {
        for (E element: this) {
            if (predicate.test(element)) return Optional.of(element);
        }

        return Optional.empty();
    }

    /**
     *
     * @param predicate search condition
     * @return no thread-safe collection
     */
    @Override
    public Collection<E> findAll(Predicate<? super E> predicate) {
        HList<E> values = new ExtendedList<>();
        for (E element: this) {
            if (predicate.test(element)) values.add(element);
        }

        return values;
    }

    // maybe check isLocked? Probably will be used in other methods
    @Override
    @SafeVarargs
    public final void addAll(E... elements) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for (E element : elements) {
                add(element);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    @SafeVarargs
    public final void setAll(E... elements) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            clear();
            for (E element : elements) {
                //noinspection UseBulkOperation
                add(element);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <C extends Collection<E>> C to(Supplier<C> collectionFactory) {
        C collection = collectionFactory.get();
        collection.addAll(this);
        return collection;
    }

    @Override
    public E firstElement() {
        return get(0);
    }

    @Override
    public E lastElement() {
        return get(size() - 1);
    }

    @Override
    public HList<E> subList(Predicate<E> condition) {
        HList<E> list = new ConcurrentList<>();
        for (E element : this) {
            if (condition.test(element)) {
                list.add(element);
            }
        }

        return list;
    }

    @Override
    public HList<E> subList(int fromIndex, int toIndex) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return new ConcurrentList<>(this, fromIndex, toIndex);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void replaceIf(Predicate<? super E> condition, E replacement) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for (int i = 0; i < size(); i++) {
                E element = get(i);
                if (condition.test(element)) {
                    set(i, replacement);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void replaceIf(Predicate<? super E> condition, UnaryOperator<E> replacer) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            for (int i = 0; i < size(); i++) {
                E element = get(i);
                if (condition.test(element)) {
                    set(i, replacer.apply(element));
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * @return thread-safe collection
     */
    @Override
    public <R> HList<R> map(Function<? super E, ? extends R> mapper) {
        HList<R> list = new ConcurrentList<>();
        for (E element : this) {
            list.add(mapper.apply(element));
        }

        return list;
    }

    @Override
    public <TH extends Exception> boolean removeIf(ProtectedPredicate<? super E, TH> filter, Class<TH> exception) throws TH {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            boolean removed = false;
            final Iterator<E> each = iterator();
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                    each.remove();
                    removed = true;
                }
            }
            return removed;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean removeIf(ProtectedBiPredicate<? super E, TH, TH2> filter, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            boolean removed = false;
            final Iterator<E> each = iterator();
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                    each.remove();
                    removed = true;
                }
            }
            return removed;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean removeIf(ProtectedTHPredicate<? super E, TH, TH2, TH3> filter, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            boolean removed = false;
            final Iterator<E> each = iterator();
            while (each.hasNext()) {
                if (filter.test(each.next())) {
                    each.remove();
                    removed = true;
                }
            }
            return removed;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <R, TH extends Exception> HList<R> map(ProtectedFunction<? super E, ? extends R, TH> mapper, Class<TH> exception) throws TH {
        HList<R> list = new ConcurrentList<>();
        for (E element : this) {
            list.add(mapper.apply(element));
        }

        return list;
    }

    @Override
    public <R, TH extends Exception, TH2 extends Exception> HList<R> map(ProtectedBiFunction<? super E, ? extends R, TH, TH2> mapper, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        HList<R> list = new ConcurrentList<>();
        for (E element : this) {
            list.add(mapper.apply(element));
        }

        return list;
    }

    @Override
    public <R, TH extends Exception, TH2 extends Exception, TH3 extends Exception> HList<R> map(ProtectedTHFunction<? super E, ? extends R, TH, TH2, TH3> mapper, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        HList<R> list = new ConcurrentList<>();
        for (E element : this) {
            list.add(mapper.apply(element));
        }

        return list;
    }

    @Override
    public boolean allMatch(Predicate<? super E> predicate) {
        for (E element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <TH extends Exception> boolean allMatch(ProtectedPredicate<? super E, TH> predicate, Class<TH> exception) throws TH {
        for (E element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean allMatch(ProtectedBiPredicate<? super E, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        for (E element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean allMatch(ProtectedTHPredicate<? super E, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        for (E element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean anyMatch(Predicate<? super E> predicate) {
        for (E element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public <TH extends Exception> boolean anyMatch(ProtectedPredicate<? super E, TH> predicate, Class<TH> exception) throws TH {
        for (E element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean anyMatch(ProtectedBiPredicate<? super E, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        for (E element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean anyMatch(ProtectedTHPredicate<? super E, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        for (E element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean noneMatch(Predicate<? super E> predicate) {
        for (E element : this) {
            if (predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <TH extends Exception> boolean noneMatch(ProtectedPredicate<? super E, TH> predicate, Class<TH> exception) throws TH {
        for (E element : this) {
            if (predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> boolean noneMatch(ProtectedBiPredicate<? super E, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        for (E element : this) {
            if (predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> boolean noneMatch(ProtectedTHPredicate<? super E, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        for (E element : this) {
            if (predicate.test(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <TH extends Exception> void forEach(ProtectedConsumer<? super E, TH> action, Class<TH> exception) throws TH {
        for (E e : this) {
            action.accept(e);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEach(ProtectedBiConsumer<? super E, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        for (E e : this) {
            action.accept(e);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEach(ProtectedTHConsumer<? super E, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        for (E e : this) {
            action.accept(e);
        }
    }

    @Override
    public E[] toArray() {
        return (E[]) super.toArray();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof HList) {
            //noinspection unchecked
            HList<E> target = (HList<E>) o;
            return new HashSet<>(this).containsAll(target) && new HashSet<>(target).containsAll(this);
        }

        return false;
    }

    @SafeVarargs
    public static <E> ConcurrentList<E> create(E... elements) {
        return new ConcurrentList<>(Arrays.asList(elements));
    }
}
