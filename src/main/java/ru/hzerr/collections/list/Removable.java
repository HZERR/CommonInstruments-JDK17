package ru.hzerr.collections.list;

import ru.hzerr.collections.functions.list.ProtectedBiPredicate;
import ru.hzerr.collections.functions.list.ProtectedPredicate;
import ru.hzerr.collections.functions.list.ProtectedTHPredicate;

@SuppressWarnings("UnusedReturnValue")
public interface Removable<T> {

    <TH extends Exception>
    boolean removeIf(ProtectedPredicate<? super T, TH> predicate, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    boolean removeIf(ProtectedBiPredicate<? super T, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    boolean removeIf(ProtectedTHPredicate<? super T, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;
}
