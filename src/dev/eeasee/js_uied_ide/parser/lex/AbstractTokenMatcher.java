package dev.eeasee.js_uied_ide.parser.lex;

public abstract class AbstractTokenMatcher implements ITokenMatcher {
    protected final char[] source;
    protected int pointer;
    protected final int initPointer;

    protected AbstractTokenMatcher(char[] source, int pointer) {
        // the analyzer would start from the pointed char.
        // E.g. source[pointer] would be the first character of the token.
        this.source = source;
        this.pointer = pointer;
        this.initPointer = pointer;
    }

    @Override
    public int getPointer() {
        return this.pointer;
    }
}
