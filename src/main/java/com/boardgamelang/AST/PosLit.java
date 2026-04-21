package com.boardgamelang.AST;

public final class PosLit extends Pos {
    public final int x;
    public final int y;

    public PosLit(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
