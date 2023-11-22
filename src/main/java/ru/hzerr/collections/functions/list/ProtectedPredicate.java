package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedPredicate<T, TH extends Exception> {

    boolean test(T t) throws TH;
}
