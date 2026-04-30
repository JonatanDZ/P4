package com.boardgamelang.interpreter;

import com.boardgamelang.AST.aexp.AexpNode;
import com.boardgamelang.AST.aexp.NumNode;
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
import com.boardgamelang.AST.pos.OffsetNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.pos.PositionNode;
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

    public Position execPos(PosNode p) {
        return switch (p) {
            case PositionNode lit -> new Position(lit.x, lit.y);
            case OffsetNode    o  -> execOffsetPos(o);
            default -> throw new UnsupportedOperationException(
                    "Pos not yet implemented: " + p.getClass().getSimpleName());
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
        Position pos = execPos(o.pos);
        return state.beta.containsKey(pos);
    }

    // public for test package: see comment on `state` above
    public int execAexp(AexpNode a) {
        return switch (a) {
            case NumNode n -> n.n;
            default -> throw new UnsupportedOperationException(
                    "Aexp not yet implemented: " + a.getClass().getSimpleName());
        };
    }

    public Position execDir(DirNode d) {
        return switch (d) {
            case UpNode    u  -> new Position(-1,  0);   // [up_BS]
            case DownNode  dn -> new Position( 1,  0);   // [down_BS]
            case RightNode r  -> new Position( 0,  1);   // [right_BS]
            case LeftNode  l  -> new Position( 0, -1);   // [left_BS]
            default -> throw new UnsupportedOperationException(
                    "Dir not yet implemented: " + d.getClass().getSimpleName());
        };
    }

    // [board_BS]: δ ← (v₁, v₂)
    private void execBoardDef(BoardNode b) {
        state.delta = execPos(b.pos);
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
        Set<State.OwnedPiece> playerPieces;
        // boolean that returns true if the piece exists in any other players list of pieces, like the semantics constraint
        boolean pieceExistsInOtherPlayers = state.o.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(node.playerIdent))
                .flatMap(entry -> entry.getValue().stream())
                .anyMatch(p -> p.piece().equals(node.pieceIdent));

        if(pieceExistsInOtherPlayers) {
            throw new RuntimeException(node.pieceIdent + " is already assigned to another player");
        } //retrieve the players set if it already exists in the state
        else if (state.o.containsKey(node.playerIdent)) {
            playerPieces = state.o.get(node.playerIdent);
        }// create a set for the player of it does not exist in the set
        else {
            // else an empty set of playerPieces is created and associated with the player
            playerPieces = new HashSet<>();
            state.o.put(node.playerIdent, playerPieces);
        }
        // This for loop will take the amount of pieces that is associated with the player, and insert it into the players hashset
        for (int i = 0; i < node.n; i++) {
            State.OwnedPiece newPiece = new State.OwnedPiece(node.pieceIdent, nextPieceId);
            playerPieces.add(newPiece);
            nextPieceId = nextPieceId + 1;
        }
    }

    private Position execOffsetPos(OffsetNode node) {
        Position base   = execPos(node.pos);
        Position dir = execDir(node.dir);
        Position result = new Position(
                base.x() + dir.x() * node.n,
                base.y() + dir.y() * node.n
        );
        // ensures that p (base) is in bound and the result position of offset is inbound. CHANGE THE SEMANTICS IN THE REPORT!
        if (0 < base.x()   && base.x()   <= state.delta.x()
            && 0 < base.y()   && base.y()   <= state.delta.y()
            && 0 < result.x() && result.x() <= state.delta.x()
            && 0 < result.y() && result.y() <= state.delta.y()) {
            return result;
        } else {
            throw new RuntimeException("offset out of bounds: " + result);
        }

    }

    private void execGamerulesPositionPieceGameRule(GamerulesPositionPieceNode gr) {
        state.g = gr.bexp;
        }

}
