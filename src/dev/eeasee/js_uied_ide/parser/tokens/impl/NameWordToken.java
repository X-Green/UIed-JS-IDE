package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

import java.util.HashMap;
import java.util.Map;

public class NameWordToken implements ITokenBase {

    private static final Map<String, ITokenBase> WORD_TOKEN_SET = new HashMap<>();

    static {
        for (KeywordToken keywordToken : KeywordToken.values()) {
            WORD_TOKEN_SET.put(keywordToken.getContent(), keywordToken);
        }
    }


    private final String content;

    private NameWordToken(String c) {
        this.content = c;
    }

    public static ITokenBase of(String content) {
        if (WORD_TOKEN_SET.containsKey(content)) {
            return WORD_TOKEN_SET.get(content);
        } else {
            ITokenBase token = new NameWordToken(content);
            WORD_TOKEN_SET.put(content, token);
            return token;
        }
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return "Name Token OF {" + this.content + "}";
    }
}
