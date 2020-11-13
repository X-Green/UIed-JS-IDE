package dev.eeasee.js_uied_ide.utils;

import java.util.Objects;

public interface CharPredicate {
    boolean test(char c);

    default CharPredicate and(CharPredicate other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default CharPredicate negate() {
        return (t) -> !test(t);
    }

    default CharPredicate or(CharPredicate other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }
}
