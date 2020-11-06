package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.err.SyntaxException;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.impl.CommentToken;
import dev.eeasee.js_uied_ide.utils.CharPredicateInstances;

public class CommentTokenMatcher extends AbstractTokenMatcher {
    private boolean startOfMultiLineTerminatorFound = false;

    private final CommentToken.Type typeOfComment;

    private final int initPointer;

    public CommentTokenMatcher(char[] source, int pointer, CommentToken.Type type) {
        super(source, pointer);

        this.typeOfComment = type;
        // the next char must be '/' or '*', as type shows

        this.initPointer = pointer;
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

            if (CharPredicateInstances.IS_LINE_TERMINATOR.test(next)) {
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
