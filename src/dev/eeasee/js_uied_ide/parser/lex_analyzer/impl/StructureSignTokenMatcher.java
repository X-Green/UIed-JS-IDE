package dev.eeasee.js_uied_ide.parser.lex_analyzer.impl;

import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.err.SyntaxException;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.AbstractTokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.impl.StructureSignToken;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;

public class StructureSignTokenMatcher extends AbstractTokenMatcher {

    private final static Char2ObjectMap<StructureSignToken> SIGNS = new Char2ObjectArrayMap<>();

    static {
        for (StructureSignToken structureSignToken : StructureSignToken.values()) {
            SIGNS.put(structureSignToken.content, structureSignToken);
        }
    }

    public StructureSignTokenMatcher(char[] source, int pointer) {
        super(source, pointer);
    }

    @Override
    public ITokenMatcher analyzeNextToken(TokenContainer container) {
        char c = this.source[this.pointer];
        StructureSignToken token = SIGNS.get(c);
        if (token == null) {
            throw new UnsupportedOperationException();
        }
        container.add(token);
        return MatcherFactory.getMatcher(this.source, this.pointer + 1, container);
    }

    public static boolean isStructureSign(char c) {
        return SIGNS.containsKey(c);
    }
}
