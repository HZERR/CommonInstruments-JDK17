package ru.hzerr.collections.map;

import ru.hzerr.collections.functions.map.ProtectedBiConsumer;
import ru.hzerr.collections.functions.map.ProtectedConsumer;
import ru.hzerr.collections.functions.map.ProtectedTHConsumer;

import java.util.function.Consumer;

public interface Streamable<K, V> {

    /**
     * Performs the specified action for each key
     * @param action the action to be performed for each key
     */
    void forEachOfKeys(Consumer<? super K> action);

    <TH extends Exception>
    void forEachOfKeys(ru.hzerr.collections.functions.list.ProtectedConsumer<? super K, TH> action, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    void forEachOfKeys(ru.hzerr.collections.functions.list.ProtectedBiConsumer<? super K, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    void forEachOfKeys(ru.hzerr.collections.functions.list.ProtectedTHConsumer<? super K, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;

    /**
     * Performs the specified action for each value
     * @param action the action to be performed for each value
     */
    void forEachOfValues(Consumer<? super V> action);

    <TH extends Exception>
    void forEachOfValues(ru.hzerr.collections.functions.list.ProtectedConsumer<? super V, TH> action, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    void forEachOfValues(ru.hzerr.collections.functions.list.ProtectedBiConsumer<? super V, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    void forEachOfValues(ru.hzerr.collections.functions.list.ProtectedTHConsumer<? super V, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;

    <TH extends Exception>
    void forEach(ProtectedConsumer<? super K, ? super V, TH> action, Class<TH> exception) throws TH;
    <TH extends Exception, TH2 extends Exception>
    void forEach(ProtectedBiConsumer<? super K, ? super V, TH, TH2> action, Class<TH> exception, Class<TH2> exception2) throws TH, TH2;
    <TH extends Exception, TH2 extends Exception, TH3 extends Exception>
    void forEach(ProtectedTHConsumer<? super K, ? super V, TH, TH2, TH3> action, Class<TH> exception, Class<TH2> exception2, Class<TH3> exception3) throws TH, TH2, TH3;
}
