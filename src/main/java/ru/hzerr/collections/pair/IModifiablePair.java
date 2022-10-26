package ru.hzerr.collections.pair;

@SuppressWarnings("unused")
public interface IModifiablePair<K, V> {

    K key();
    V value();

    void key(K key);
    void value(V value);
}
