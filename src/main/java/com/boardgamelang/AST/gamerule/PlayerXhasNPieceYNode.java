package com.boardgamelang.AST.gamerule;

public final class PlayerXHasNPieceYNode extends GameRuleNode {
    public final String playerIdent;
    public final int n;
    public final String pieceIdent;

    public PlayerXhasNPieceYNode(String playerIdent, int n, String pieceIdent) {
        this.playerIdent = playerIdent;
        this.n = n;
        this.pieceIdent = pieceIdent;
    }
}
