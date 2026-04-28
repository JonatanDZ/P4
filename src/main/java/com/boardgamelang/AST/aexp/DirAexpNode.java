package com.boardgamelang.AST.aexp;

import com.boardgamelang.AST.direction.Direction;

public final class DirAexpNode extends AexpNode {
    public final Direction dir;

    public DirAexpNode(Direction dir) {
        this.dir = dir;
    }
}
