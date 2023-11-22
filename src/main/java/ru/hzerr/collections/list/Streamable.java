package ru.hzerr.collections.list;

import ru.hzerr.collections.functions.list.*;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Streamable<T> {

    /**
     * @see java.util.stream.Stream#map(Function)
     */
    <R> HList<R> map(Function<? super T, ? extends R> mapper);
    <R, TH extends Exception> HList<R> map(ProtectedFunction<? super T, ? extends R, TH> mapper, Class<TH> exception) throws TH;
    <R, TH extends Exception, TH2 extends Exception>
    HList<R> map(ProtectedBiFunction<? super T, ? extends R, TH, TH2> mapper, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <R, TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    HList<R> map(ProtectedTHFunction<? super T, ? extends R, TH, TH2, TH3> mapper, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;
    boolean allMatch(Predicate<? super T> predicate);
    <TH extends Exception> boolean allMatch(ProtectedPredicate<? super T, TH> predicate, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    boolean allMatch(ProtectedBiPredicate<? super T, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    boolean allMatch(ProtectedTHPredicate<? super T, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;
    boolean anyMatch(Predicate<? super T> predicate);
    <TH extends Exception> boolean anyMatch(ProtectedPredicate<? super T, TH> predicate, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    boolean anyMatch(ProtectedBiPredicate<? super T, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    boolean anyMatch(ProtectedTHPredicate<? super T, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;
    boolean noneMatch(Predicate<? super T> predicate);
    <TH extends Exception> boolean noneMatch(ProtectedPredicate<? super T, TH> predicate, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    boolean noneMatch(ProtectedBiPredicate<? super T, TH, TH2> predicate, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    boolean noneMatch(ProtectedTHPredicate<? super T, TH, TH2, TH3> predicate, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;

    <TH extends Exception> void forEach(ProtectedConsumer<? super T, TH> action, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    void forEach(ProtectedBiConsumer<? super T, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    void forEach(ProtectedTHConsumer<? super T, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;
}
