package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.impl.NameWordToken;

public class WordTokenMatcher extends AbstractTokenMatcher {

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
                NameWordToken.of(
                        new String(
                                this.source, this.initPointer, this.pointer - this.initPointer
                        )));
    }
}
