package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedBiFunction<T, R, TH extends Exception, TH2 extends Exception> {

    R apply(T t) throws TH, TH2;
}
