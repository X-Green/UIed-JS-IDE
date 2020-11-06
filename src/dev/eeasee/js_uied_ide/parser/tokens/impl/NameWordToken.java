package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

import java.util.HashMap;
import java.util.Map;

public class NameWordToken implements ITokenBase {

    private final String content;

    public NameWordToken(String c) {
        this.content = c;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return "Name Token OF {" + this.content + "}";
    }
}
