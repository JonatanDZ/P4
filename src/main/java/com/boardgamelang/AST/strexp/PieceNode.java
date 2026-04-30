package com.boardgamelang.AST.strexp;

import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.pos.PositionNode;

public class PieceNode extends StrexpNode {
    public final PositionNode pos;

    public PieceNode(PositionNode pos) {
        this.pos = pos;
    }
}
