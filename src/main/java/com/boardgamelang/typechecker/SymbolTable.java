package com.boardgamelang.typechecker;

public class SymbolTable {
    public int boardWidth = -1;
    public int boardHeight = -1;

    public boolean isBoardDefined() {
        return boardWidth != -1 && boardHeight != -1;
    }
}
