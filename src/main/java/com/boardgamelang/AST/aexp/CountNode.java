package com.boardgamelang.AST.aexp;

public class CountNode extends AexpNode{
    public final String ident;

    public CountNode(String ident) {
        this.ident = ident;
    }
}
