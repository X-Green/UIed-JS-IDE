package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;

public class LiteralStringTokenMatcher extends AbstractTokenMatcher {

    private final boolean isDoubleQuote;

    public LiteralStringTokenMatcher(char[] source, int pointer, boolean isDoubleQuote) {
        super(source, pointer);
        this.isDoubleQuote = isDoubleQuote;
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        return null;
    }
}
