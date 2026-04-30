package com.boardgamelang.AST.bexp;

public final class AndNode extends BexpNode {
    public final BexpNode left;
    public final BexpNode right;

    public AndNode(BexpNode left, BexpNode right) {
        this.left = left;
        this.right = right;
    }
}
