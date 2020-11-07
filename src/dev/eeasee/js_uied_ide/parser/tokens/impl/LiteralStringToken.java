package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

public class LiteralStringToken implements ITokenBase {
    public final String content;

    public final Type type;


    public static LiteralStringToken of(String content, Type type) {
        return new LiteralStringToken(content, type);
    }

    private LiteralStringToken(String content, Type type) {
        this.content = content;
        this.type = type;
    }

    public enum Type {
        SINGLE_QUOTE('\''),
        DOUBLE_QUOTE('\"');

        public final char content;
        Type(char c) {
            this.content = c;
        }
    }

    @Override
    public String toString() {
        return "Literal String Token OF {" + this.content + "}";
    }
}
