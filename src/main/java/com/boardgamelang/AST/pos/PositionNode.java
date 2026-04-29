package com.boardgamelang.AST.pos;

public final class PositionNode extends PosNode {
    public final int x;
    public final int y;

    public PositionNode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
