package ru.hzerr.collections.pair;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("unused")
public class ModifiablePair<K, V> implements IModifiablePair<K, V> {

    private K key;
    private V value;

    public ModifiablePair(K key, V value) { this.key = key; this.value = value; }
    public ModifiablePair() {}

    @Override
    public K key() {
        return this.key;
    }

    @Override
    public V value() {
        return this.value;
    }

    @Override
    public void key(K key) {
        this.key = key;
    }

    @Override
    public void value(V value) {
        this.value = value;
    }

    public static <K, V> ModifiablePair<K, V> from(K key, V value) {
        return new ModifiablePair<>(key, value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ModifiablePair<?, ?> that = (ModifiablePair<?, ?>) o;

        return new EqualsBuilder().append(key, that.key).append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(key)
                .append(value)
                .toHashCode();
    }
}
