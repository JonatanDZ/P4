package com.boardgamelang.AST.bexp;

public final class OccupiedNode extends Bexp {
    public final int x;
    public final int y;

    public OccupiedNode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
