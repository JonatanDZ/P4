package com.boardgamelang.AST.bexp;

import com.boardgamelang.AST.pos.PosNode;

public final class OccupiedNode extends BexpNode {
    public final PosNode pos;

    public OccupiedNode(PosNode pos) {
        this.pos = pos;
    }
}