package com.boardgamelang.AST.strexp;

import com.boardgamelang.AST.pos.PosNode;

public class PieceNode extends StrexpNode {
    public final PosNode pos;

    public PieceNode(PosNode pos) {
        this.pos = pos;
    }
}
