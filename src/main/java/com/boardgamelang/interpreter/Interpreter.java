package com.boardgamelang.interpreter;

import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.bexp.OrNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.StmtNode;

public final class Interpreter {
    // public for test package: allows for mocking states. Should be private final..
    public final State state = new State();

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
    private void execStmt(StmtNode stmt) {
        switch (stmt) {
            case AssertNode a -> execAssertStmt(a);
            default -> throw new UnsupportedOperationException(
                    "stmt not yet implemented: " + stmt.getClass().getSimpleName());
        }
    }


    // public for test package: see comment on `state` above
    public boolean execBexp(BexpNode bexp) {
        return switch (bexp) {
            case OccupiedNode o -> execOccupiedBExp(o);
            case AndNode a -> execBexp(a.left) && execBexp(a.right);
            case OrNode o -> execBexp(o.left) || execBexp(o.right);
            default -> throw new UnsupportedOperationException(
                    "Bexp not yet implemented: " + bexp.getClass().getSimpleName());
        };
    }

    private void execAssertStmt(AssertNode a) {
        boolean bexp = execBexp(a.bexp);
        state.t.put(a.ident, bexp);
    }

    private boolean execOccupiedBExp(OccupiedNode o) {
        Position pos = new Position(o.pos.x, o.pos.y);
        return state.beta.containsKey(pos);
    }

    // [board_BS]: δ ← (v₁, v₂)
    private void execBoardDef(BoardNode b) {
        state.delta = new Position(b.pos.x, b.pos.y);
    }


}
