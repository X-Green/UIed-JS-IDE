package dev.eeasee.js_uied_ide.parser.lex_analyzer;

public abstract class AbstractTokenMatcher implements ITokenMatcher {
    protected final char[] source;
    protected int pointer = 0;

    protected AbstractTokenMatcher(char[] source, int pointer) {
        // the analyzer would start from the pointed char.
        // E.g. source[pointer] would be the first character of the token.
        this.source = source;
        this.pointer = pointer;
    }

    @Override
    public int getPointer() {
        return this.pointer;
    }

    public class CharIteratorImpl implements CharIterator {
        @Override
        public char next() {
            try {
                char c = source[pointer];
                pointer++;
                return c;
            } catch (ArrayIndexOutOfBoundsException e) {
                return (char) (4); // EOT
            }
        }

        @Override
        public void setPointer(int p) {
            pointer = p;
        }
    }

    public interface CharIterator {
        char next();

        void setPointer(int p);
    }
}
