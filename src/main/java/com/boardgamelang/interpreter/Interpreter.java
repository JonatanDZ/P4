package com.boardgamelang.interpreter;

import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.direction.DirNode;
import com.boardgamelang.AST.direction.DownNode;
import com.boardgamelang.AST.direction.LeftNode;
import com.boardgamelang.AST.direction.RightNode;
import com.boardgamelang.AST.direction.UpNode;
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

    // currently unused in interpreter which is on purpose. Should be called in offset etc.
    public Position execDir(DirNode d) {
        return switch (d) {
            case LeftNode  l  -> new Position(-1,  0);
            case RightNode r  -> new Position( 1,  0);
            case UpNode    u  -> new Position( 0,  1);
            case DownNode  dn -> new Position( 0, -1);
            default -> throw new UnsupportedOperationException(
                    "Dir not yet implemented: " + d.getClass().getSimpleName());
        };
    }

    // [board_BS]: δ ← (v₁, v₂)
    private void execBoardDef(BoardNode b) {
        state.delta = new Position(b.pos.x, b.pos.y);
    }


}
