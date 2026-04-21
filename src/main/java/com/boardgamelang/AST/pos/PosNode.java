package com.boardgamelang.AST.pos;

import com.boardgamelang.AST.Node;
import com.boardgamelang.AST.bexp.Bexp;

public class PosNode extends Node {
    public final int x;
    public final int y;

    public PosNode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
