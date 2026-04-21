package com.boardgamelang.AST;

public final class Place extends Stmt {
    public final String piece;
    public final Pos pos;

    public Place(String piece, Pos pos) {
        this.piece = piece;
        this.pos = pos;
    }
}
