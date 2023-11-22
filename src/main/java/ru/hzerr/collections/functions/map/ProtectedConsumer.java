package ru.hzerr.collections.functions.map;

@FunctionalInterface
public interface ProtectedConsumer<K, V, TH extends Exception> {

    void accept(K key, V value) throws TH;
}

