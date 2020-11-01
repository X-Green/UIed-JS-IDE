package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

public class LineTerminatorToken implements ITokenBase {
    public static final LineTerminatorToken INSTANCE = new LineTerminatorToken(){
        @Override
        public String toString() {
            return "Line Terminator Token";
        }
    };
}
