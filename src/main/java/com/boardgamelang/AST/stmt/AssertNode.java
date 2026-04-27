package com.boardgamelang.AST.stmt;

import com.boardgamelang.AST.bexp.BexpNode;

public class AssertNode extends StmtNode {
    public final String ident;
    public final BexpNode bexp;

    public AssertNode(String ident, BexpNode bexp) {
        this.ident = ident;
        this.bexp = bexp;
    }
}
