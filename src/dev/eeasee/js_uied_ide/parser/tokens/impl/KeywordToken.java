package dev.eeasee.js_uied_ide.parser.tokens.impl;

import dev.eeasee.js_uied_ide.parser.tokens.ITokenBase;

public enum KeywordToken implements ITokenBase {
    ABSTRACT,
    ARGUMENTS,
    BOOLEAN,
    BREAK,
    BYTE,
    CASE,
    CATCH,
    CHAR,
    CLASS,
    CONST,
    CONTINUE,
    DEBUGGER,
    DEFAULT,
    DELETE,
    DO,
    DOUBLE,
    ELSE,
    ENUM,
    EVAL,
    EXPORT,
    EXTENDS,
    FALSE,
    FINAL,
    FINALLY,
    FLOAT,
    FOR,
    FUNCTION,
    GOTO,
    IF,
    IMPLEMENTS,
    IMPORT,
    IN,
    INSTANCEOF,
    INT,
    INTERFACE,
    LET,
    LONG,
    NATIVE,
    NEW,
    NULL,
    PACKAGE,
    PRIVATE,
    PROTECTED,
    PUBLIC,
    RETURN,
    SHORT,
    STATIC,
    SUPER,
    SWITCH,
    SYNCHRONIZED,
    THIS,
    THROW,
    THROWS,
    TRANSIENT,
    TRUE,
    TRY,
    TYPEOF,
    VAR,
    VOID,
    VOLATILE,
    WHILE,
    WITH,
    YIELD,

    GET_CLASS("getClass"),
    JAVA,
    JAVA_ARRAY("JavaArray"),
    JAVA_CLASS("JavaClass"),
    JAVA_OBJECT("JavaObject"),
    JAVA_PACKAGE("JavaPackage");


    private final String content;

    KeywordToken() {
        this.content = this.name().toLowerCase();
    }

    KeywordToken(String s) {
        this.content = s;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return "Keyword Token OF {" + this.content + "}";
    }
}
