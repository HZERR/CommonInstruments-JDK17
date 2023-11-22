package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedBiConsumer<T, TH extends Exception, TH2 extends Exception> {

    void accept(T t) throws TH, TH2;
}
