package ru.hzerr.collections.functions.list;

@FunctionalInterface
public interface ProtectedTHConsumer<T, TH extends Exception, TH2 extends Exception, TH3 extends Exception> {

    void accept(T t) throws TH, TH2, TH3;
}
