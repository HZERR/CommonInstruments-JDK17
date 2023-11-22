package ru.hzerr.collections.functions.map;

@FunctionalInterface
public interface ProtectedBiConsumer<K, V, TH extends Exception, TH2 extends Exception> {

    void accept(K key, V value) throws TH, TH2;
}
