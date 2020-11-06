package dev.eeasee.js_uied_ide.parser.lex_analyzer;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.err.SyntaxException;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.impl.*;
import dev.eeasee.js_uied_ide.parser.tokens.impl.CommentToken;
import dev.eeasee.js_uied_ide.parser.tokens.impl.LineTerminatorToken;
import dev.eeasee.js_uied_ide.utils.CharPredicateInstances;

public class MatcherFactory {
    public static ITokenMatcher getMatcher(char[] source, int pointer, TokenContainer tokenList) {
        char c;

        try {
            c = source[pointer];
            while (Character.isWhitespace(c)) {
                if (CharPredicateInstances.IS_LINE_TERMINATOR.test(c)) {
                    tokenList.add(LineTerminatorToken.INSTANCE);
                }
                pointer++;
                c = source[pointer];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

        switch (c) {
            case '/':
                try {
                    char peak = source[pointer + 1];
                    CommentToken.Type commentTokenType;
                    switch (peak) {
                        case '/':
                            commentTokenType = CommentToken.Type.SINGLE_LINE;
                            break;
                        case '*':
                            commentTokenType = CommentToken.Type.MULTI_LINE;
                            break;
                        default:
                            throw new SyntaxException();
                    }
                    return new CommentTokenMatcher(source, pointer, commentTokenType);
                } catch (ArrayIndexOutOfBoundsException arrayE) {
                    return null;
                }
            case '\"':
                return new LiteralStringTokenMatcher(source, pointer, true);
            case '\'':
                return new LiteralStringTokenMatcher(source, pointer, false);
        }
        if (Character.isDigit(c)) {
            return new LiteralNumberTokenMatcher(source, pointer);
        }
        if (Character.isUnicodeIdentifierStart(c)) {
            return new WordTokenMatcher(source, pointer);
        }
        if (StructureSignTokenMatcher.isStructureSign(c)) {
            return new StructureSignTokenMatcher(source, pointer);
        }
        return new OperatorTokenMatcher(source, pointer);
    }
}
