package com.boardgamelang.AST.stmt;

import com.boardgamelang.AST.pos.PosNode;

public final class PlacePieceXAtPNode extends StmtNode{
    public final String X;
    public final PosNode Pos;

    public PlacePieceXAtPNode(PosNode Pos, String X) {
        this.X = X;
        this.Pos = Pos;
    }
}
