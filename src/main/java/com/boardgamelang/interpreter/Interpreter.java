package com.boardgamelang.interpreter;

import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.program.ProgramNode;
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
        state.delta = new Position(node.pos.x, node.pos.y);
    }

    private void execStmt(StmtNode stmt) {
        throw new UnsupportedOperationException(
                "stmt not yet implemented: " + stmt.getClass().getSimpleName());
    }
}
