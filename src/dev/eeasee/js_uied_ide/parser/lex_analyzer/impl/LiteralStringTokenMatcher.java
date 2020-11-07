package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.err.SyntaxException;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.impl.LiteralStringToken;

public class LiteralStringTokenMatcher extends AbstractTokenMatcher {

    private final LiteralStringToken.Type quoteType;

    public LiteralStringTokenMatcher(char[] source, int pointer, LiteralStringToken.Type quoteType) {
        super(source, pointer);
        this.quoteType = quoteType;
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        while (true) {
            if (this.pointer >= this.source.length) {
                throw new SyntaxException();
            }
            if (this.pointer == this.initPointer) {
                this.pointer++;
                continue;
            }
            char c = this.source[this.pointer];
            if (c == this.quoteType.content) {
                this.pointer++;
                container.add(LiteralStringToken.of(new String(this.source, this.initPointer, this.pointer - this.initPointer), this.quoteType));
                return MatcherFactory.getMatcher(this.source, this.pointer, container);
            }
            this.pointer++;
        }
    }
}
