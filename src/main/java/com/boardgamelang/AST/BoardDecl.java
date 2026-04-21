package com.boardgamelang.AST;

public final class BoardDecl extends Def {
    public final int width;
    public final int height;

    public BoardDecl(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
