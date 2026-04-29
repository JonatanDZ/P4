package com.boardgamelang.AST.def;

import com.boardgamelang.AST.pos.PosNode;

public final class BoardNode extends DefNode {
    public final PosNode pos;

    public BoardNode(PosNode pos) {
        this.pos = pos;
    }
}

