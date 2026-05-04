package com.boardgamelang.AST.bexp;


import com.boardgamelang.AST.Node;

public class EqualityNode extends BexpNode {
    public final Node left;
    public final Node right;

    public EqualityNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}
