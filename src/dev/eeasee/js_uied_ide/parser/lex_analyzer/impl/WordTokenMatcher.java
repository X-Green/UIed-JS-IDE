package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;
import dev.eeasee.js_uied_ide.parser.tokens.impl.KeywordToken;
import dev.eeasee.js_uied_ide.parser.tokens.impl.NameWordToken;

import java.util.HashMap;
import java.util.Map;

public class WordTokenMatcher extends AbstractTokenMatcher {

    private static final Map<String, ITokenBase> WORD_TOKEN_SET = new HashMap<>();

    static {
        for (KeywordToken keywordToken : KeywordToken.values()) {
            WORD_TOKEN_SET.put(keywordToken.getContent(), keywordToken);
        }
    }

    public static ITokenBase getWordTokenOrCreate(String content) {
        if (WORD_TOKEN_SET.containsKey(content)) {
            return WORD_TOKEN_SET.get(content);
        } else {
            ITokenBase token = new NameWordToken(content);
            WORD_TOKEN_SET.put(content, token);
            return token;
        }
    }


    public WordTokenMatcher(char[] source, int pointer) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        while (true) {
            if (this.pointer >= this.source.length) {
                putTokenInContainer(container);
                return null;
            }
            if (!Character.isUnicodeIdentifierPart(this.source[pointer])) {
                putTokenInContainer(container);
                return MatcherFactory.getMatcher(this.source, this.pointer, container);
            }
            this.pointer++;
        }
    }

    private void putTokenInContainer(TokenContainer container) {
        container.add(
                getWordTokenOrCreate(
                        new String(
                                this.source, this.initPointer, this.pointer - this.initPointer
                        )));
    }
}
