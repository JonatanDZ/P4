package com.boardgamelang.AST.aexp;

public final class NumNode extends AexpNode {
    // int (not double): board game values are inherently discrete — positions, counts, offsets are all integer-valued
    public final int n;

    public NumNode(int n) {
        this.n = n;
    }
}
