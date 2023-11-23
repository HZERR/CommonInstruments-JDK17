package ru.hzerr.collections.map;

import java.util.Map;

public interface HMap<K, V> extends Map<K, V>,
        Finder<K, V>,
        Receiver<K, V>,
        Contender<K, V>,
        Streamable<K, V> {

    V putAndGet(K key, V value);
    V putIfAbsentAndGet(K key, V value);

    static <K, V> HMap<K, V> createExtendedMap() { return new ExtendedMap<>(); }
    static <K, V> HMap<K, V> createProtectedMap() { return new SynchronizedMap<>(); }

    static <K, V> HMap<K, V> create(Type type) {
        return switch (type) {
            case EXTENDED -> createExtendedMap();
            case SYNCHRONIZED -> createProtectedMap();
        };
    }
}
