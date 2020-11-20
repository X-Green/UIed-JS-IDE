package dev.eeasee.js_uied_ide.parser.lex.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.tokens.impl.OperatorToken;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;

import java.util.EnumSet;
import java.util.Set;

public class OperatorTokenMatcher extends AbstractTokenMatcher {

    public static Char2ObjectOpenHashMap<Set<OperatorToken>> FIRST_CHAR_TO_OPERATOR_TOKENS = new Char2ObjectOpenHashMap<>();

    private OperatorToken operatorTillLastChar = null;

    private Set<OperatorToken> operatorTokenCandidate = EnumSet.noneOf(OperatorToken.class);

    public OperatorTokenMatcher(char[] source, int pointer) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        while (true) {
            if (this.pointer >= this.source.length) {
                container.add(this.operatorTillLastChar);
            }

            if (operatorTillLastChar == null) {

            }
        }
    }
}
