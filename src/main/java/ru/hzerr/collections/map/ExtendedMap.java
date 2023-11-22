package ru.hzerr.collections.map;

import ru.hzerr.collections.functions.list.ProtectedBiConsumer;
import ru.hzerr.collections.functions.list.ProtectedConsumer;
import ru.hzerr.collections.functions.list.ProtectedTHConsumer;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class ExtendedMap<K, V> extends HashMap<K, V> implements HMap<K, V> {

    public ExtendedMap() { super(); }
    public ExtendedMap(Map<? extends K, ? extends V> map) { super(map); }
    public ExtendedMap(int initialCapacity) { super(initialCapacity); }
    public ExtendedMap(int initialCapacity, float loadFactor) { super(initialCapacity, loadFactor); }

    @Override
    public K findKey(Predicate<K> predicate) {
        for (K k: this.keySet()) {
            if (predicate.test(k)) {
                return k;
            }
        }

        return null;
    }

    @Override
    public V findValue(Predicate<V> predicate) {
        for (V v: values()) {
            if (predicate.test(v)) {
                return v;
            }
        }

        return null;
    }

    @Override
    public V findValueByKey(Predicate<K> predicate) {
        for (K k: this.keySet()) {
            if (predicate.test(k)) {
                return this.get(k);
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Predicate<K> predicate) {
        for (K k: keySet()) {
            if (predicate.test(k)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(Predicate<V> predicate) {
        for (V v: values()) {
            if (predicate.test(v)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean noContainsKey(K key) { return !containsKey(key); }

    @Override
    public boolean noContainsValue(V value) { return !containsValue(value); }

    @Override
    public boolean noContainsKey(Predicate<K> predicate) { return !containsKey(predicate); }

    @Override
    public boolean noContainsValue(Predicate<K> predicate) { return !containsValue(predicate); }

    @Override
    public void forEachOfKeys(Consumer<? super K> consumer) {
        this.keySet().forEach(consumer);
    }

    @Override
    public <TH extends Exception> void forEachOfKeys(ProtectedConsumer<? super K, TH> action, Class<TH> exception) throws TH {
        for (K key : this.keySet()) {
            action.accept(key);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEachOfKeys(ProtectedBiConsumer<? super K, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        for (K key : this.keySet()) {
            action.accept(key);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEachOfKeys(ProtectedTHConsumer<? super K, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        for (K key : this.keySet()) {
            action.accept(key);
        }
    }

    @Override
    public void forEachOfValues(Consumer<? super V> consumer) {
        this.values().forEach(consumer);
    }

    @Override
    public <TH extends Exception> void forEachOfValues(ProtectedConsumer<? super V, TH> action, Class<TH> exception) throws TH {
        for (V value : this.values()) {
            action.accept(value);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEachOfValues(ProtectedBiConsumer<? super V, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        for (V value : this.values()) {
            action.accept(value);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEachOfValues(ProtectedTHConsumer<? super V, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        for (V value : this.values()) {
            action.accept(value);
        }
    }

    @Override
    public <TH extends Exception> void forEach(ru.hzerr.collections.functions.map.ProtectedConsumer<? super K, ? super V, TH> action, Class<TH> exception) throws TH {
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEach(ru.hzerr.collections.functions.map.ProtectedBiConsumer<? super K, ? super V, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEach(ru.hzerr.collections.functions.map.ProtectedTHConsumer<? super K, ? super V, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }

    @Override
    public <R> R getStateKeyIf(Function<? super K, R> function, Predicate<R> predicate) {
        for (K k: keySet()) {
            R r = function.apply(k);
            if (predicate.test(r)) {
                return r;
            }
        }

        return null;
    }

    @Override
    public <R> R getStateKeyIf(Predicate<K> predicate, Function<? super K, R> function) {
        for (K k: keySet()) {
            if (predicate.test(k)) {
                return function.apply(k);
            }
        }

        return null;
    }

    @Override
    public <R> R getStateValueIf(Function<? super V, R> function, Predicate<R> predicate) {
        for (V v: values()) {
            R r = function.apply(v);
            if (predicate.test(r)) {
                return r;
            }
        }

        return null;
    }

    @Override
    public <R> R getStateValueIf(Predicate<V> predicate, Function<? super V, R> function) {
        for (V v: values()) {
            if (predicate.test(v)) {
                return function.apply(v);
            }
        }

        return null;
    }

    @Override
    public V putAndGet(K key, V value) {
        put(key, value);
        return value;
    }

    @Override
    public V putIfAbsentAndGet(K key, V value) {
        putIfAbsent(key, value);
        return value;
    }
}
