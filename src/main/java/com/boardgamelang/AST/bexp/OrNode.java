package com.boardgamelang.AST.bexp;

public final class OrNode extends BexpNode {
    public final BexpNode left;
    public final BexpNode right;

    public OrNode(BexpNode left, BexpNode right) {
        this.left = left;
        this.right = right;
    }
}