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
    YIELD;

    private final String realName;

    KeywordToken() {
        this.realName = this.name().toLowerCase();
    }

    public String getRealName() {
        return this.realName;
    }
}
