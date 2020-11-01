package dev.eeasee.js_uied_ide.parser.lex_analyzer;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;

public interface ITokenMatcher {
    ITokenMatcher analyzeNextToken(TokenContainer container);

    int getPointer();
}
