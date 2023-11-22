package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedFunction<T, R, TH extends Exception> {

    R apply(T t) throws TH;
}
