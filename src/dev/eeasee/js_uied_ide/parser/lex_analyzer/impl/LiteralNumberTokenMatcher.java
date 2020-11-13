package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.err.SyntaxException;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.impl.NumberToken;
import dev.eeasee.js_uied_ide.utils.CharPredicate;

public class LiteralNumberTokenMatcher extends AbstractTokenMatcher {


    private NumberToken.Type numberType = NumberToken.Type.DECIMAL;

    public LiteralNumberTokenMatcher(char[] source, int pointer) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        while (true) {

            if (this.pointer >= this.source.length) {
                NumberToken token = NumberToken.of(this.concludeNumber(), this.numberType);
                container.add(token);
                return null;
            }

            if (this.pointer == this.initPointer && this.source[this.pointer] == '0') {
                this.numberType = NumberToken.Type.OCTAL;
            }

            if (this.pointer == this.initPointer + 2 && this.numberType == NumberToken.Type.OCTAL) {
                switch (this.source[this.pointer]) {
                    case 'b':
                    case 'B':
                        this.numberType = NumberToken.Type.BINARY;
                        this.pointer++;
                        continue;

                    case 'x':
                    case 'X':
                        this.numberType = NumberToken.Type.HEXADECIMAL;
                        this.pointer++;
                        continue;
                }
            }

            char c = this.source[this.pointer];
            if (c == 'E' || c == 'e') {
                if (this.numberType != NumberToken.Type.DECIMAL) {
                    throw new SyntaxException();
                }
                this.numberType = NumberToken.Type.EXPONENTIATION;
                this.pointer++;
                continue;
            }

            if (!Character.isJavaIdentifierPart(c) && (c != '.') && (c != '-')) {
                NumberToken token = NumberToken.of(this.concludeNumber(), this.numberType);
                container.add(token);
                return MatcherFactory.getMatcher(this.source, this.pointer, container);
            }

            this.pointer++;
        }
    }

    private String concludeNumber() {
        String num = new String(this.source, this.initPointer, this.pointer-this.initPointer);
        //TODO: Verify the number
        return num;
    }

    private static boolean isBinNumberPart(char c) {
        return (c == '0') || (c == '1') || (c == 'B') || (c == 'b');
    }

    private static boolean isOctNumberPart(char c) {
        return (c >= '0' && c <= '7');
    }

    private static boolean isDecNumberPart(char c) {
        return (c >= '0' && c <= '9') || (c == 'E') || (c == 'e');
    }

    private static boolean isHexNumberPart(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c == 'X') || (c == 'x');
    }


}
