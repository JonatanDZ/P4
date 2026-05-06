package com.boardgamelang.interpreter;

import com.boardgamelang.AST.bexp.*;
import com.boardgamelang.AST.aexp.AexpNode;
import com.boardgamelang.AST.aexp.NumNode;
import com.boardgamelang.AST.bexp.*;
import com.boardgamelang.AST.gamerule.DrawWhenGlobalNode;
import com.boardgamelang.AST.gamerule.GameRuleNode;
import com.boardgamelang.AST.gamerule.WinWhenPositionsNode;
import com.boardgamelang.AST.aexp.CountNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.AST.strexp.PieceNode;
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
import com.boardgamelang.AST.pos.PositionRefNode;
import com.boardgamelang.AST.gamerule.GamerulesPositionPieceNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.PlacePieceAtNode;
import com.boardgamelang.AST.stmt.StmtNode;
import com.boardgamelang.AST.strexp.StrexpNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public final class Interpreter {
    // public for test package: allows for mocking states. Should be private final..
    public final State state = new State();

    public State run(ProgramNode program) {
        execDef(program.defnode);
        for (GameRuleNode gameRule : program.gameRuleNodes) {
            execGameRule(gameRule);
        }
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


    private void execGameRule(GameRuleNode gameRule) {
        switch (gameRule) {
            case PlayerHasPieceNode p -> execPlayerHasPieceGameRule(p);
            case WinWhenPositionsNode w -> execWinWhenPositionsGameRule(w);
            case DrawWhenGlobalNode dg -> execDrawWhenGlobalGameRule(dg);
            case GamerulesPositionPieceNode gr -> execGamerulesPositionPieceGameRule(gr);
            default -> throw new UnsupportedOperationException(
                    "gamerule not yet implemented: " + gameRule.getClass().getSimpleName());
        }
    }

    private void execStmt(StmtNode stmt) {
        switch (stmt) {
            case PlacePieceAtNode p -> execPlacePieceAtStmt(p);
            case AssertNode a -> execAssertStmt(a);
            default -> throw new UnsupportedOperationException(
                    "stmt not yet implemented: " + stmt.getClass().getSimpleName());
        }
    }

    public long execAexp(AexpNode aexp) {
        return switch (aexp) {
            case CountNode c -> execCountNode(c);
            case NumNode n -> n.n;
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
            case NotNode n -> !execBexp(n.b);
            case EqualityNode e -> execEqualityNode(e);
            default -> throw new UnsupportedOperationException(
                    "Bexp not yet implemented: " + bexp.getClass().getSimpleName());
        };
    }

    public boolean execEqualityNode(EqualityNode e) {
        return switch (e.left) {
            case AexpNode l -> execAexp(l) == execAexp((AexpNode) e.right);
            case StrexpNode l -> execStrexp(l).equals(execStrexp((StrexpNode) e.right));
            case PosNode l -> execPos(l).equals(execPos((PosNode) e.right));
            default -> throw new RuntimeException("Unsupported equality check");
        };
    }

    public Position execPos(PosNode p) {
        return switch (p) {
            case PositionNode    lit -> new Position(lit.x, lit.y);
            case OffsetNode      o   -> execOffsetPos(o);
            case PositionRefNode r   -> execPositionRef();
            default -> throw new UnsupportedOperationException(
                    "Pos not yet implemented: " + p.getClass().getSimpleName());
        };
    }

    private Position execPositionRef() {
        Object v = state.sigma.get("position");
        if (!(v instanceof Position pos)) {
            throw new RuntimeException(
                    "'position' is not bound — only valid inside a gamerule, win, or draw bexp during place piece");
        }
        return pos;
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
        Position pos = execPos(p.pos);

        return state.beta.get(pos);
    }

    private long execCountNode(CountNode count) {
        // Looks in beta and count the amount of appearances of the piece

        return state.beta.values().stream()
                .filter(piece -> piece.equals(count.ident))
                .count();
    }

    private int nextPieceId = 0;

    private void execWinWhenPositionsGameRule(WinWhenPositionsNode node) {
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

    private void execDrawWhenGlobalGameRule(DrawWhenGlobalNode node){
        state.eta = node.bexp;
    }

    public void execPlacePieceAtStmt(PlacePieceAtNode node) {
        String pieceName = node.ident;

        boolean exists = state.o.values().stream()
                .flatMap(Set::stream)
                .anyMatch(p -> p.piece().equals(pieceName));
        if (!exists) {
            throw new RuntimeException("Piece not owned: " + pieceName);
        }

        Position pos = execPos(node.pos);
        if (pos.x() <= 0 || pos.x() > state.delta.x() ||
                pos.y() <= 0 || pos.y() > state.delta.y()){
            throw new RuntimeException("Out of bounds: " + pos);
        }

        // this is σ' = σ[position ↦ pos]. It does not create intermediary state, but it cleans up after itself; essentially doing the same.
        state.sigma.put("position", pos);
        try {
            // code is purposefully ugly. execBexp has to be run after throwing custom exception, given that g is null; else it throws ambigious exception.
            if (state.g == null) {
                throw new RuntimeException("Invalid action: Game Rules are not declared");
            }
            boolean b1 = execBexp(state.g);
            if (!b1) {
                throw new RuntimeException("Invalid action: Game rule is false and the piece can not be placed");
            }

            state.beta.put(pos, pieceName);

            // check win conditions
            boolean b2 = state.w != null && execBexp(state.w);
            // check draw conditions
            boolean b3 = state.eta != null && execBexp(state.eta);
            if(b2 && !state.sigma.containsKey("win")){
                // piece ∈ o(player): find the player who owns the piece just placed to associate the player to the win
                String winner = state.o.entrySet().stream()
                        .filter(entry -> entry.getValue().stream().anyMatch(p -> p.piece().equals(pieceName)))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No player owns piece: " + pieceName));
                state.sigma.put("win", winner);
            } else if(b3){
                state.sigma.put("draw", true);
            }
        } finally {
            state.sigma.remove("position");
        }
    }
}

