package com.boardgamelang.AST.stmt;

import com.boardgamelang.AST.pos.PosNode;

public final class PlacePieceXAtPNode extends StmtNode{
    public final String ident;
    public final PosNode pos;

    public PlacePieceXAtPNode(PosNode pos, String ident) {
        this.ident = ident;
        this.pos = pos;
    }
}
