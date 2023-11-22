package ru.hzerr.collections.map;

import ru.hzerr.collections.functions.list.ProtectedBiConsumer;
import ru.hzerr.collections.functions.list.ProtectedConsumer;
import ru.hzerr.collections.functions.list.ProtectedTHConsumer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.*;

public class ProtectedMap<K, V> extends ExtendedMap<K, V> implements HMap<K, V>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final transient Object mutex;

    public ProtectedMap() { super(); mutex = this; }
    public ProtectedMap(Object mutex) { super(); this.mutex = mutex; }
    public ProtectedMap(Map<? extends K, ? extends V> map) { super(map); mutex = this; }
    public ProtectedMap(Map<? extends K, ? extends V> map, Object mutex) { super(map); this.mutex = mutex; }
    public ProtectedMap(int initialCapacity) { super(initialCapacity); mutex = this; }
    public ProtectedMap(int initialCapacity, Object mutex) { super(initialCapacity); this.mutex = mutex; }
    public ProtectedMap(int initialCapacity, float loadFactor) { super(initialCapacity, loadFactor); mutex = this; }
    public ProtectedMap(int initialCapacity, float loadFactor, Object mutex) { super(initialCapacity, loadFactor); this.mutex = mutex; }

    @Override
    public V putAndGet(K key, V value) {
        synchronized (mutex) {
            return super.putAndGet(key, value);
        }
    }

    @Override
    public V putIfAbsentAndGet(K key, V value) {
        synchronized (mutex) {
            return super.putIfAbsentAndGet(key, value);
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
    public boolean containsKey(Object key) {
        synchronized (mutex) {
            return super.containsKey(key);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        synchronized (mutex) {
            return super.containsValue(value);
        }
    }

    @Override
    public V get(Object key) {
        synchronized (mutex) {
            return super.get(key);
        }
    }

    @Override
    public V put(K key, V value) {
        synchronized (mutex) {
            return super.put(key, value);
        }
    }

    @Override
    public V remove(Object key) {
        synchronized (mutex) {
            return super.remove(key);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronized (mutex) {
            super.putAll(m);
        }
    }

    @Override
    public void clear() {
        synchronized (mutex) {
            super.clear();
        }
    }

    @Override
    public Set<K> keySet() {
        synchronized (mutex) {
            return super.keySet();
        }
    }

    @Override
    public Collection<V> values() {
        synchronized (mutex) {
            return super.values();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        synchronized (mutex) {
            return super.entrySet();
        }
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        synchronized (mutex) {
            return super.getOrDefault(key, defaultValue);
        }
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        synchronized (mutex) {
            super.forEach(action);
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        synchronized (mutex) {
            super.replaceAll(function);
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        synchronized (mutex) {
            return super.putIfAbsent(key, value);
        }
    }

    @Override
    public boolean remove(Object key, Object value) {
        synchronized (mutex) {
            return super.remove(key, value);
        }
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        synchronized (mutex) {
            return super.replace(key, oldValue, newValue);
        }
    }

    @Override
    public V replace(K key, V value) {
        synchronized (mutex) {
            return super.replace(key, value);
        }
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        synchronized (mutex) {
            return super.computeIfAbsent(key, mappingFunction);
        }
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        synchronized (mutex) {
            return super.computeIfPresent(key, remappingFunction);
        }
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        synchronized (mutex) {
            return super.compute(key, remappingFunction);
        }
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        synchronized (mutex) {
            return super.merge(key, value, remappingFunction);
        }
    }

    @Override
    public boolean containsKey(Predicate<K> predicate) {
        synchronized (mutex) {
            return super.containsKey(predicate);
        }
    }

    @Override
    public boolean containsValue(Predicate<V> predicate) {
        synchronized (mutex) {
            return super.containsValue(predicate);
        }
    }

    @Override
    public boolean noContainsKey(K key) {
        synchronized (mutex) {
            return super.noContainsKey(key);
        }
    }

    @Override
    public boolean noContainsValue(V value) {
        synchronized (mutex) {
            return super.noContainsValue(value);
        }
    }

    @Override
    public boolean noContainsKey(Predicate<K> predicate) {
        synchronized (mutex) {
            return super.containsKey(predicate);
        }
    }

    @Override
    public boolean noContainsValue(Predicate<K> predicate) {
        synchronized (mutex) {
            return super.noContainsValue(predicate);
        }
    }

    @Override
    public K findKey(Predicate<K> predicate) {
        synchronized (mutex) {
            return super.findKey(predicate);
        }
    }

    @Override
    public V findValue(Predicate<V> predicate) {
        synchronized (mutex) {
            return super.findValue(predicate);
        }
    }

    @Override
    public V findValueByKey(Predicate<K> predicate) {
        synchronized (mutex) {
            return super.findValueByKey(predicate);
        }
    }

    @Override
    public <R> R getStateKeyIf(Function<? super K, R> function, Predicate<R> predicate) {
        synchronized (mutex) {
            return super.getStateKeyIf(function, predicate);
        }
    }

    @Override
    public <R> R getStateKeyIf(Predicate<K> predicate, Function<? super K, R> function) {
        synchronized (mutex) {
            return super.getStateKeyIf(predicate, function);
        }
    }

    @Override
    public <R> R getStateValueIf(Function<? super V, R> function, Predicate<R> predicate) {
        synchronized (mutex) {
            return super.getStateValueIf(function, predicate);
        }
    }

    @Override
    public <R> R getStateValueIf(Predicate<V> predicate, Function<? super V, R> function) {
        synchronized (mutex) {
            return super.getStateValueIf(predicate, function);
        }
    }

    @Override
    public void forEachOfKeys(Consumer<? super K> action) {
        synchronized (mutex) {
            super.forEachOfKeys(action);
        }
    }

    @Override
    public <TH extends Exception> void forEachOfKeys(ProtectedConsumer<? super K, TH> action, Class<TH> exception) throws TH {
        synchronized (mutex) {
            super.forEachOfKeys(action, exception);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEachOfKeys(ProtectedBiConsumer<? super K, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            super.forEachOfKeys(action, exception, exception2);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEachOfKeys(ProtectedTHConsumer<? super K, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            super.forEachOfKeys(action, exception, exception2, exception3);
        }
    }

    @Override
    public void forEachOfValues(Consumer<? super V> action) {
        synchronized (mutex) {
            super.forEachOfValues(action);
        }
    }

    @Override
    public <TH extends Exception> void forEachOfValues(ProtectedConsumer<? super V, TH> action, Class<TH> exception) throws TH {
        synchronized (mutex) {
            super.forEachOfValues(action, exception);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEachOfValues(ProtectedBiConsumer<? super V, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            super.forEachOfValues(action, exception, exception2);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEachOfValues(ProtectedTHConsumer<? super V, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            super.forEachOfValues(action, exception, exception2, exception3);
        }
    }

    @Override
    public <TH extends Exception> void forEach(ru.hzerr.collections.functions.map.ProtectedConsumer<? super K, ? super V, TH> action, Class<TH> exception) throws TH {
        synchronized (mutex) {
            super.forEach(action, exception);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception> void forEach(ru.hzerr.collections.functions.map.ProtectedBiConsumer<? super K, ? super V, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2 {
        synchronized (mutex) {
            super.forEach(action, exception, exception2);
        }
    }

    @Override
    public <TH extends Exception, TH2 extends Exception, TH3 extends Exception> void forEach(ru.hzerr.collections.functions.map.ProtectedTHConsumer<? super K, ? super V, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3 {
        synchronized (mutex) {
            super.forEach(action, exception, exception2, exception3);
        }
    }

    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
        synchronized (mutex) {
            s.defaultWriteObject();
        }
    }
}
