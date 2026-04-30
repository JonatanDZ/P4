package com.boardgamelang.AST.bexp;


import com.boardgamelang.AST.Node;

public class EqualsNode extends BexpNode {
    public final Node left;
    public final Node right;

    public EqualsNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}
