package ru.hzerr.collections.pair;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("unused")
public record UnmodifiablePair<K, V>(K key, V value) implements IUnmodifiablePair<K, V> {

    public static <K, V> UnmodifiablePair<K, V> create(K key, V value) {
        return new UnmodifiablePair<>(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UnmodifiablePair<?, ?> that = (UnmodifiablePair<?, ?>) o;

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
