package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.lex_analyzer.impl.OperatorTokenMatcher;
import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

import java.util.Collection;
import java.util.EnumSet;

public enum OperatorToken implements ITokenBase {
    ASSIGNMENT                      ("=", OperatorType.ASSIGNMENT),
    ADDITION_ASSIGNMENT             ("+=", OperatorType.ASSIGNMENT),
    SUBTRACTION_ASSIGNMENT          ("-=", OperatorType.ASSIGNMENT),
    MULTIPLICATION_ASSIGNMENT       ("*=", OperatorType.ASSIGNMENT),
    DIVISION_ASSIGNMENT             ("/=", OperatorType.ASSIGNMENT),
    REMAINDER_ASSIGNMENT            ("%=", OperatorType.ASSIGNMENT),
    EXPONENTIATION_ASSIGNMENT       ("**=", OperatorType.ASSIGNMENT),
    LEFT_SHIFT_ASSIGNMENT           ("<<=", OperatorType.ASSIGNMENT),
    RIGHT_SHIFT_ASSIGNMENT          (">>=", OperatorType.ASSIGNMENT),
    UNSIGNED_RIGHT_SHIFT_ASSIGNMENT (">>>=", OperatorType.ASSIGNMENT),
    BITWISE_AND_ASSIGNMENT          ("&=", OperatorType.ASSIGNMENT),
    BITWISE_XOR_ASSIGNMENT          ("^=", OperatorType.ASSIGNMENT),
    BITWISE_OR_ASSIGNMENT           ("|=", OperatorType.ASSIGNMENT),
    LOGICAL_AND_ASSIGNMENT          ("&&=", OperatorType.ASSIGNMENT),
    LOGICAL_OR_ASSIGNMENT           ("||=", OperatorType.ASSIGNMENT),
    LOGICAL_NULLISH_ASSIGNMENT      ("??=", OperatorType.ASSIGNMENT),

    EQUAL                           ("==", OperatorType.COMPARISON),
    NOT_EQUAL                       ("!=", OperatorType.COMPARISON),
    STRICT_EQUAL                    ("===", OperatorType.COMPARISON),
    STRICT_NOT_EQUAL                ("!==", OperatorType.COMPARISON),
    GREATER_THAN                    (">", OperatorType.COMPARISON),
    GREATER_THAN_OR_EQUAL           (">=", OperatorType.COMPARISON),
    LESS_THAN                       ("<", OperatorType.COMPARISON),
    LESS_THAN_OR_EQUAL              ("<=", OperatorType.COMPARISON),

    ARROW_FUNCTION_EXPRESSION       ("=>", OperatorType.FUNCTION_EXPR),

    REMAINDER                       ("%", OperatorType.ARITHMETIC),
    INCREMENT                       ("++", OperatorType.ARITHMETIC),
    DECREMENT                       ("--", OperatorType.ARITHMETIC),
    UNARY_NEGATION                  ("-", OperatorType.ARITHMETIC),
    UNARY_PLUS                      ("+", OperatorType.ARITHMETIC),
    EXPONENTIATION_OPERATOR         ("**", OperatorType.ARITHMETIC),

    BITWISE_AND                     ("&", OperatorType.BITWISE),
    BITWISE_OR                      ("|", OperatorType.BITWISE),
    BITWISE_XOR                     ("^", OperatorType.BITWISE),
    BITWISE_NOT                     ("~", OperatorType.BITWISE),
    LEFT_SHIFT                      ("<<", OperatorType.BITWISE),
    SIGN_PROPAGATING_RIGHT_SHIFT    (">>", OperatorType.BITWISE),
    ZERO_FILL_RIGHT_SHIFT           (">>>", OperatorType.BITWISE),

    LOGICAL_AND                     ("&&", OperatorType.LOGICAL),
    LOGICAL_OR                      ("||", OperatorType.LOGICAL),
    LOGICAL_NOT                     ("!", OperatorType.LOGICAL),

    CONDITIONAL_QUESTION_MARK       ("?", OperatorType.CONDITIONAL);


    private final String content;
    private final OperatorType type;

    private boolean sealed = false;
    public final EnumSet<OperatorToken> tokensPrefixingThis = EnumSet.noneOf(OperatorToken.class);

    private OperatorToken(String s, OperatorType type) {

        this.content = s;
        this.type = type;
    }

    public void seal() {
        for (OperatorToken token : OperatorToken.values()) {
            String s = token.content;
            if (s.length() > this.content.length()) {
                if (s.startsWith(this.content)) {
                    tokensPrefixingThis.add(token);
                }
            }
        }
        OperatorTokenMatcher.TOKEN_SET_LIST.get(this.content.length()).add(this);
        sealed = true;
    }

    public Collection<OperatorToken> getTokensPrefixingThis() {
        if (!sealed) {
            throw new UnsupportedOperationException();
        }
        return this.tokensPrefixingThis;
    }

    public enum OperatorType {
        ASSIGNMENT,
        COMPARISON,
        FUNCTION_EXPR,
        ARITHMETIC,
        BITWISE,
        LOGICAL,
        CONDITIONAL;


        private OperatorType() {

        }
    }
}
