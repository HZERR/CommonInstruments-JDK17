package ru.hzerr.collections.functions.map;

@FunctionalInterface
public interface ProtectedTHConsumer<K, V, TH extends Exception, TH2 extends Exception, TH3 extends Exception> {

    void accept(K key, V value) throws TH, TH2, TH3;
}