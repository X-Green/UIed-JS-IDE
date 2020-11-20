package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.lex.impl.OperatorTokenMatcher;
import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;
import dev.eeasee.js_uied_ide.utils.ObjectArray;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.chars.CharArraySet;
import it.unimi.dsi.fastutil.chars.CharSet;

import java.util.HashSet;
import java.util.Set;

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
    public Char2ObjectMap<ObjectArray<OperatorToken>> tokensPrefixingThis = new Char2ObjectOpenHashMap<>();

    static {
        for (OperatorToken operatorToken : OperatorToken.values()) {
            operatorToken.seal();
        }
    }

    OperatorToken(String s, OperatorType type) {
        this.content = s;
        this.type = type;
        addToFirstCharToSetMap();
    }

    private void addToFirstCharToSetMap() {
        char key = this.content.charAt(0);
        Set<OperatorToken> tokenSet;
        if (OperatorTokenMatcher.FIRST_CHAR_TO_OPERATOR_TOKENS.containsKey(key)) {
            tokenSet = OperatorTokenMatcher.FIRST_CHAR_TO_OPERATOR_TOKENS.get(key);
        } else {
            tokenSet = new HashSet<>();
            OperatorTokenMatcher.FIRST_CHAR_TO_OPERATOR_TOKENS.put(key, tokenSet);
        }
        tokenSet.add(this);
    }

    private void seal() {
        Char2ObjectMap<Set<OperatorToken>> char2TokenSetMap = new Char2ObjectOpenHashMap<>();
        for (OperatorToken tokenSubThis : OperatorToken.values()) {
            String s = tokenSubThis.content;
            if (s.length() > this.content.length()) {
                if (s.startsWith(this.content)) {
                    char key = tokenSubThis.content.charAt(this.content.length());
                    Set<OperatorToken> tokenSet;
                    if (char2TokenSetMap.containsKey(key)) {
                        tokenSet = char2TokenSetMap.get(key);
                    } else {
                        tokenSet = new HashSet<>();
                        char2TokenSetMap.put(key, tokenSet);
                    }
                    tokenSet.add(tokenSubThis);
                }
            }
        }
        for (Char2ObjectMap.Entry<Set<OperatorToken>> entry : char2TokenSetMap.char2ObjectEntrySet()) {
            Set<OperatorToken> tokenSet = entry.getValue();
            char charKey = entry.getCharKey();
            ObjectArray<OperatorToken> tokenArrayObject = new ObjectArray<>(tokenSet.toArray());
            this.tokensPrefixingThis.put(charKey, tokenArrayObject);
        }

        if (this.tokensPrefixingThis.isEmpty()) {
            this.tokensPrefixingThis = null;
        }

        sealed = true;
    }

    public ObjectArray<OperatorToken> getTokensPrefixingThisAtCharOf(char c) {
        if (!sealed) {
            throw new UnsupportedOperationException();
        }
        return this.tokensPrefixingThis.getOrDefault(c, null);
    }

    public String getContent() {
        return content;
    }

    public OperatorType getType() {
        return type;
    }

    public enum OperatorType {
        ASSIGNMENT,
        COMPARISON,
        FUNCTION_EXPR,
        ARITHMETIC,
        BITWISE,
        LOGICAL,
        CONDITIONAL;


        OperatorType() {

        }
    }
}
