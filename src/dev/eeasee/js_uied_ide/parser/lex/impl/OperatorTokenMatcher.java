package dev.eeasee.js_uied_ide.parser.lex.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.tokens.impl.OperatorToken;

import java.util.ArrayList;
import java.util.EnumSet;

public class OperatorTokenMatcher extends AbstractTokenMatcher {

    public OperatorTokenMatcher(char[] source, int pointer) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        return null;
    }

    public final static ArrayList<EnumSet<OperatorToken>> TOKEN_SET_LIST = new ArrayList<>(4);

    static {
        for (int i = 0; i < 4; i++) {
            TOKEN_SET_LIST.add(EnumSet.noneOf(OperatorToken.class));
        }
    }
}
