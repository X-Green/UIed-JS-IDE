package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;

public class CommonWordTokenMatcher extends AbstractTokenMatcher {
    public CommonWordTokenMatcher(char[] source, int pointer, boolean mayBeJavaIdentifier) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        return null;
    }
}
