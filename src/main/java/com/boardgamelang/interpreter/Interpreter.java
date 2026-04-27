package com.boardgamelang.interpreter;

import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.PlacePieceXAtPNode;
import com.boardgamelang.AST.stmt.StmtNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        Object value = state.sigma.get(node.ident);
        if (!(value instanceof String pieceName)) {
            throw new RuntimeException("Expected piece identifier: " + node.ident);
        }

        boolean exists = state.o.values().stream()
                .flatMap(Set::stream)
                .anyMatch(p -> p.piece().equals(pieceName));
        if (!exists) {
            throw new RuntimeException("Piece not owned: " + pieceName);
        }

        Position pos = new Position(node.pos.x, node.pos.y);
        if (pos.x() <= 0 || pos.x() > state.delta.x() ||
                pos.y() <= 0 || pos.y() > state.delta.y()){
            throw new RuntimeException("Out of bounds: " + pos);
        }

        Map<String, Object> sigmaPrime = new HashMap<>(state.sigma);
        sigmaPrime.put("position", pos);
        sigmaPrime.put("piece", pieceName);

        BexpNode b1 = state.g;


        state.beta.put(pos, node.ident);
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


}
