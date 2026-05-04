package com.boardgamelang.typechecker;

public class TypeException extends RuntimeException {
    // This is for convenience in tests, that the error specifically comes from the type checker. 
    public TypeException(String message) {
        super(message);
    }
}
