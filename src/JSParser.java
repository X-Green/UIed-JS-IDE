import dev.eeasee.js_uied_ide.parser.container.TokenContainer;
import dev.eeasee.js_uied_ide.parser.lex.ITokenMatcher;
import dev.eeasee.js_uied_ide.parser.lex.MatcherFactory;
import dev.eeasee.js_uied_ide.parser.tokens.impl.OperatorToken;
import dev.eeasee.js_uied_ide.utils.ObjectArray;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;

public class JSParser {
    public static void main(String[] args) {

    }

    public static void tryParse() {
        char[] source = ("/*abcd233*/\n\'233333\'\n\n{//axsdbhxxhsdbdh\nxxx 233 1E-23 0.33 0b10 0xAB} " +
                " yyy this\nfb234").toCharArray();
        TokenContainer container = new TokenContainer();

        ITokenMatcher matcher = MatcherFactory.getMatcher(source, 0, container);

        while (matcher != null) {
            matcher = matcher.analyzeNextToken(container);
        }

        container.forEach(System.out::println);
    }

    public static void tryRunScript() {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            scriptEngine.eval(new FileReader("test/test_direct_run.js"));
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
