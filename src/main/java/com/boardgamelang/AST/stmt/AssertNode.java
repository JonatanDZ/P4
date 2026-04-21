package com.boardgamelang.AST.stmt;

import com.boardgamelang.AST.Node;

public class AssertNode extends Stmt{
    public final String ident;
    public final Node bexp;

    public AssertNode(String ident, Node bexp) {
        this.ident = ident;
        this.bexp = bexp;
    }
}
