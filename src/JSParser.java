import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex_analyzer.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

import java.io.File;
import java.util.ArrayList;

public class JSParser {
    public static void main(String[] args) {
        char[] source = "/*abcd233*/\n\n\n//axsdbhxxhsdbdh\nxxx".toCharArray();
        TokenContainer container = new TokenContainer();

        ITokenMatcher matcher = MatcherFactory.getMatcher(source, 0, container);
        matcher = matcher.analyzeNextToken(container);
        matcher = matcher.analyzeNextToken(container);

        container.forEach(System.out::println);
    }
}
