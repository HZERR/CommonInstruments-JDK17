package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedTHFunction<T, R, TH extends Exception, TH2 extends Exception, TH3 extends Exception> {

    R apply(T t) throws TH, TH2, TH3;
}
