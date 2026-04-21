package com.boardgamelang.AST.def;

public final class BoardNode extends Def {
    public final int width;
    public final int height;

    public BoardNode(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
