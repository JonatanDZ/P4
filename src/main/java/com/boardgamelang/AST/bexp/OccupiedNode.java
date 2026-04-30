package com.boardgamelang.AST.bexp;

import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.pos.PositionNode;

public final class OccupiedNode extends BexpNode {
    public final PositionNode pos;

    public OccupiedNode(PositionNode pos) {
        this.pos = pos;
    }
}