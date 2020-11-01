package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.tokens.impl.StructureSignToken;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

public class StructureSignTokenMatcher extends AbstractTokenMatcher {


    public StructureSignTokenMatcher(char[] source, int pointer) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        return null;
    }

    private final static Int2ObjectMap<StructureSignToken> SIGNS = new Int2ObjectArrayMap<>();

    public static void addStructureSign(char c, StructureSignToken token) {
        SIGNS.put(c, token);
    }

    public static boolean isStructureSign(char c) {
        return SIGNS.containsKey(c);
    }
}
