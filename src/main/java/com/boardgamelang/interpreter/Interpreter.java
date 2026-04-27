package com.boardgamelang.interpreter;

import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.gamerule.PlayerHasPieceNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.StmtNode;

import java.util.HashSet;
import java.util.Set;

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
            case PlayerHasPieceNode p -> execPlayerHasPieceGameRule(p);
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

    private boolean execOccupiedBExp(OccupiedNode o) {
        Position pos = new Position(o.pos.x, o.pos.y);
        return state.beta.containsKey(pos);
    }


    // [board_BS]: δ ← (v₁, v₂)
    private void execBoardDef(BoardNode node) {
        state.delta = new Position(node.pos.x, node.pos.y);
    }

    private int nextPieceId = 0;

    private void execPlayerHasPieceGameRule(PlayerHasPieceNode node) {
        Set<State.OwnedPiece> playerPieces = state.o.computeIfAbsent(node.playerIdent, player -> new HashSet<>());
        for (int i = 0; i < node.n; i++) {
            playerPieces.add(new State.OwnedPiece(node.pieceIdent, nextPieceId++));
        }
    }
}
