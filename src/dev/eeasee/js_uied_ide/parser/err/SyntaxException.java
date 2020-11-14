package dev.eeasee.js_uied_ide.parser.err;

public class SyntaxException extends RuntimeException {
    Integer pointer;

    public SyntaxException() {

    }

    public SyntaxException(int pointer) {
        this.pointer = pointer;
    }

    @Override
    public String getMessage() {
        if (this.pointer == null) {
            return "";
        } else {
            return "at" + this.pointer;
        }
    }

}
