package dev.eeasee.js_uied_ide.utils;

import it.unimi.dsi.fastutil.chars.CharSet;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

public class CharPredicateInstances {

    public static final CharPredicate IS_JAVA_IDENTIFIER_START = Character::isJavaIdentifierStart;
    public static final CharPredicate IS_JAVA_IDENTIFIER_PART = Character::isJavaIdentifierPart;
    public static final CharPredicate IS_UNICODE_IDENTIFIER_START = Character::isUnicodeIdentifierStart;
    public static final CharPredicate IS_UNICODE_IDENTIFIER_PART = Character::isUnicodeIdentifierPart;

    public static final CharPredicate CAN_APPEAR_AFTER_ASSIGNMENT = null;

    public static final CharPredicate IS_LINE_TERMINATOR = c -> (c == '\n' || c == '\r' || c == '\u2028' || c == '\u2029');

    @FunctionalInterface
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

    public static class CharSets {

    }
}
