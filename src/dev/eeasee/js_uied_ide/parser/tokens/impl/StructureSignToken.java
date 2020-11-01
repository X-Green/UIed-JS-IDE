package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.impl.StructureSignTokenMatcher;

public enum StructureSignToken implements ITokenBase {
    LEFT_ROUND_BRACKET      ('('),
    LEFT_SQUARE_BRACKET     ('['),
    LEFT_SQUIGGLY_BRACKET   ('{'),

    RIGHT_ROUND_BRACKET     (')'),
    RIGHT_SQUARE_BRACKET    (']'),
    RIGHT_SQUIGGLY_BRACKET  ('}'),

    COLON                   (':'),
    SEMI_COLON              (';'),
    COMMA                   (','),
    DOT                     ('.');


    StructureSignToken(char c) {
        StructureSignTokenMatcher.addStructureSign(c, this);
    }

}