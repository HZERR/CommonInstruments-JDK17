package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedConsumer<T, TH extends Exception> {

    void accept(T t) throws TH;
}
