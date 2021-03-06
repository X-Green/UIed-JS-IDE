package dev.eeasee.js_uied_ide.parser.lex.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.err.SyntaxException;
import dev.eeasee.js_uied_ide.parser.lex.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.impl.CommentToken;

public class CommentTokenMatcher extends AbstractTokenMatcher {
    private boolean startOfMultiLineTerminatorFound = false;

    private final CommentToken.Type typeOfComment;

    public CommentTokenMatcher(char[] source, int pointer, CommentToken.Type type) {
        super(source, pointer);

        this.typeOfComment = type;
        // the next char must be '/' or '*', as type shows
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        while (true) {
            if (this.pointer >= this.source.length) {
                if (this.typeOfComment == CommentToken.Type.SINGLE_LINE) {
                    String co = new String(this.source, this.initPointer, this.pointer - this.initPointer);
                    container.add(CommentToken.of(co, CommentToken.Type.SINGLE_LINE));
                    return null;
                } else {
                    throw new SyntaxException();
                }
            }

            char next = this.source[this.pointer];

            if (this.startOfMultiLineTerminatorFound) {
                if (next == '/') {
                    this.pointer++;
                    String co = new String(this.source, this.initPointer, this.pointer - this.initPointer);
                    container.add(CommentToken.of(co, CommentToken.Type.MULTI_LINE));
                    return MatcherFactory.getMatcher(this.source, this.pointer, container);
                } else {
                    this.startOfMultiLineTerminatorFound = (next == '*');
                    continue;
                }
            }

            if (MatcherFactory.IS_LINE_TERMINATOR.test(next)) {
                if (this.typeOfComment == CommentToken.Type.SINGLE_LINE) {
                    String co = new String(this.source, this.initPointer, this.pointer - this.initPointer);
                    container.add(CommentToken.of(co, CommentToken.Type.SINGLE_LINE));
                    return MatcherFactory.getMatcher(this.source, this.pointer, container);
                }
            }

            if (next == '*') {
                if (this.typeOfComment == CommentToken.Type.MULTI_LINE) {
                    this.startOfMultiLineTerminatorFound = true;
                }
            }

            this.pointer++;
        }
    }


}
