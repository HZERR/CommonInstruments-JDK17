package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedTHPredicate<T, TH extends Exception, TH2 extends Exception, TH3 extends Exception> {

    boolean test(T t) throws TH, TH2, TH3;
}
