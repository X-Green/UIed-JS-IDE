package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;

public class LiteralNumberTokenMatcher extends AbstractTokenMatcher {
    public LiteralNumberTokenMatcher(char[] source, int pointer) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        return null;
    }
}
