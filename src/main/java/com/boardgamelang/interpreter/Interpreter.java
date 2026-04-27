package com.boardgamelang.interpreter;

import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.PlacePieceXAtPNode;
import com.boardgamelang.AST.stmt.StmtNode;

public final class Interpreter {
    private final State state = new State();

    public State run(ProgramNode program) {
        execDef(program.defnode);
        for (StmtNode stmt : program.stmtNodes) {
            execStmt(stmt);
        }
        return state;
    }

    private void execDef(DefNode def) {
        switch (def) {
            case BoardNode b -> execBoardDef(b);
            default -> throw new UnsupportedOperationException(
                    "def not yet implemented: " + def.getClass().getSimpleName());
        }
    }

    // [board_BS]: δ ← (v₁, v₂)
    private void execBoardDef(BoardNode node) {
        state.delta = new Position(node.width, node.height);
    }

    private void execStmt(StmtNode stmt) {
        switch (stmt) {
            case PlacePieceXAtPNode p -> execPlacePieceAtStmt(p);
            default -> throw new UnsupportedOperationException(
                    "stmt not yet implemented: " + stmt.getClass().getSimpleName());
        }
    }

    private void execPlacePieceAtStmt(PlacePieceXAtPNode node) {
        Object piece = state.o.get(node.ident);
        if (!(piece  )) {
            throw new RuntimeException("Expected piece: " + node.ident);
        }

        Position pos = node.pos;
        if (pos.x() <= 0 || pos.x() > state.delta.x() ||
                pos.y() <= 0 || pos.y() > state.delta.y()){
            throw new RuntimeException("Out of bounds!" + pos);
        }

        state.beta.put(new Position(node.pos.x(), node.pos.y()), node.ident);

    }
}
