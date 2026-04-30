package com.boardgamelang.AST.pos;

import com.boardgamelang.AST.direction.DirNode;

public final class OffsetNode extends PosNode {
    public final PosNode pos;
    public final DirNode dir;
    public final int n;

    public OffsetNode(PosNode pos, DirNode dir, int n) {
        this.pos = pos;
        this.dir = dir;
        this.n = n;
    }
}
