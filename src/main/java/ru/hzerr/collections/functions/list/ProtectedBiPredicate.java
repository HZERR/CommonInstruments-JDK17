package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedBiPredicate<T, TH extends Exception, TH2 extends Exception> {

    boolean test(T t) throws TH, TH2;
}
