package ru.hzerr.collections.map;

import ru.hzerr.collections.pair.IUnmodifiablePair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class HashHMap<K, V> extends HashMap<K, V> implements HMap<K, V> {

    public HashHMap() { super(); }
    public HashHMap(Map<? extends K, ? extends V> map) { super(map); }
    public HashHMap(int initialCapacity) { super(initialCapacity); }
    public HashHMap(int initialCapacity, float loadFactor) { super(initialCapacity, loadFactor); }

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
    public void forKEach(Consumer<? super K> consumer) {
        this.keySet().forEach(consumer);
    }

    @Override
    public void forVEach(Consumer<? super V> consumer) {
        this.values().forEach(consumer);
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

    @SafeVarargs
    public static <K, V> HashHMap<K, V> create(IUnmodifiablePair<K, V>... pairs) {
        HashHMap<K, V> instance = new HashHMap<>();
        for (IUnmodifiablePair<K, V> pair: pairs) {
            instance.put(pair.key(), pair.value());
        }

        return instance;
    }
}
