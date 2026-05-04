package com.boardgamelang.AST.bexp;

public final class NotNode extends BexpNode {
    public final BexpNode b;

    public NotNode(BexpNode b) {
        this.b = b;
    }
}
