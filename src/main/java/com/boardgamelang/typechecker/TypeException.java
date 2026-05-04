package com.boardgamelang.typechecker;

public class TypeException extends RuntimeException {
    // This is an alias for RuntimeException. It is not strictly necessary. TypeException does not differ from RuntimeException semantically.
    // It adds readability in code by separating RuntimeExceptions and TypeExceptions (exceptions at compile time).
    public TypeException(String message) {
        super(message);
    }
}
