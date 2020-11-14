package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

import java.util.HashMap;
import java.util.Map;

public class NumberToken implements ITokenBase {

    private static final Map<String, NumberToken> NUMBER_TOKEN_STRING_MAP = new HashMap<>();

    private final Type numberType;

    private final String content;

    public static NumberToken of(String content, Type numberType) {
        if (NUMBER_TOKEN_STRING_MAP.containsKey(content)) {
            return NUMBER_TOKEN_STRING_MAP.get(content);
        } else {
            NumberToken token = new NumberToken(content, numberType);
            NUMBER_TOKEN_STRING_MAP.put(content, token);
            return token;
        }
    }

    private NumberToken(String content, Type numberType) {
        this.content = content;
        this.numberType = numberType;
    }

    public Type getType() {
        return numberType;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Number Token OF {" + this.content + "} AS "+ this.numberType.toString();
    }

    public enum Type {
        DECIMAL         (10),
        BINARY          (2),
        OCTAL           (8),
        HEXADECIMAL     (16),
        EXPONENTIATION  (10);

        public final int radix;

        Type(int radix) {
            this.radix = radix;
        }
    }
}
