package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

import java.util.HashMap;
import java.util.Map;

public class NameToken implements ITokenBase {
    private static final Map<String, NameToken> LITERAL_TOKEN_POOL = new HashMap<>();

    private final String content;

    private NameToken(String c) {
        this.content = c;
    }

    public String getContent() {
        return this.content;
    }

    public static NameToken of(String c) {
        if (LITERAL_TOKEN_POOL.containsKey(c)) {
            return LITERAL_TOKEN_POOL.get(c);
        } else {
            NameToken token = new NameToken(c);
            LITERAL_TOKEN_POOL.put(c, token);
            return token;
        }
    }
}
