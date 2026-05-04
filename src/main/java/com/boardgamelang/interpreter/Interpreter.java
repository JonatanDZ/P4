package com.boardgamelang.interpreter;

import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.gamerule.WinWhenPositionsNode;
import com.boardgamelang.AST.aexp.AexpNode;
import com.boardgamelang.AST.aexp.CountNode;
import com.boardgamelang.AST.strexp.PieceNode;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.bexp.OrNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.direction.DirNode;
import com.boardgamelang.AST.direction.DownNode;
import com.boardgamelang.AST.direction.LeftNode;
import com.boardgamelang.AST.direction.RightNode;
import com.boardgamelang.AST.direction.UpNode;
import com.boardgamelang.AST.gamerule.PlayerHasPieceNode;
import com.boardgamelang.AST.gamerule.GameRuleNode;
import com.boardgamelang.AST.gamerule.GamerulesPositionPieceNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.StmtNode;
import com.boardgamelang.AST.strexp.StrexpNode;

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
            case WinWhenPositionsNode w -> execWinWhenPositionsGameRule(w);
            case AssertNode a -> execAssertStmt(a);
            case GameRuleNode g -> execGameRule(g);
            default -> throw new UnsupportedOperationException(
                    "stmt not yet implemented: " + stmt.getClass().getSimpleName());
        }
    }

    private void execGameRule(GameRuleNode gameRule) {
        switch (gameRule){
            case GamerulesPositionPieceNode gr -> execGamerulesPositionPieceGameRule(gr);
            default -> throw new UnsupportedOperationException(
                    "gameRule not yet implemented: " + gameRule.getClass().getSimpleName());
        }
    }
    public long execAexp(AexpNode aexp) {
        return switch (aexp) {
            case CountNode c -> execCountNode(c);
            default -> throw new UnsupportedOperationException(
                    "aexp not yet implemented: " + aexp.getClass().getSimpleName());
        };
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

    public String execStrexp(StrexpNode strexp) {
        return switch (strexp){
            case PieceNode p -> execPieceStrexp(p);
            default -> throw new UnsupportedOperationException(
                    "strexp not yet implemented: " + strexp.getClass().getSimpleName());
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

    private String execPieceStrexp(PieceNode p) {
        Position pos = new Position(p.pos.x, p.pos.y);

        String pieceAtPosition = state.beta.get(pos);
        return pieceAtPosition;
    }

    private long execCountNode(CountNode count) {
        // Looks in beta and count the amount of appearances of the piece
        long amountOfAppearances = state.beta.values().stream()
                .filter(piece -> piece.equals(count.ident))
                .count();

        return amountOfAppearances;
    }

    private int nextPieceId = 0;

    private void execWinWhenPositionsGameRule(WinWhenPositionsNode node) {
        // rn state is limited to onle be declared once, maybe it should be changed?
        if (state.w != null) {
            throw new RuntimeException("WinWhenPositionsGameRule already defined, redefine WinWhenPositions to add more win conditions");
        }
        state.w = node.bexp;
    }

    private void execPlayerHasPieceGameRule(PlayerHasPieceNode node) {
        // type checker guarantees piece is not assigned to another player — get or create the player's set
        if (!state.o.containsKey(node.playerIdent)) {
            state.o.put(node.playerIdent, new HashSet<>());
        }
        Set<State.OwnedPiece> playerPieces = state.o.get(node.playerIdent);
        for (int i = 0; i < node.n; i++) {
            playerPieces.add(new State.OwnedPiece(node.pieceIdent, nextPieceId++));
        }
    }

    private void execGamerulesPositionPieceGameRule(GamerulesPositionPieceNode gr) {
        state.g = gr.bexp;
        }

}
