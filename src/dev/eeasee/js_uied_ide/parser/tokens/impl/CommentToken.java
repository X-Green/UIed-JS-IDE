package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

public class CommentToken implements ITokenBase {

    public final String content;

    public final Type type;


    public static CommentToken of(String content, Type type) {
        return new CommentToken(content, type);
    }

    private CommentToken(String content, Type type) {
        this.content = content;
        this.type = type;
    }

    public enum Type {
        SINGLE_LINE,
        MULTI_LINE
    }

    @Override
    public String toString() {
        return "Comment Token OF {" + this.content + "}";
    }
}
