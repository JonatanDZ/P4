package com.boardgamelang.typechecker;

import com.boardgamelang.AST.Node;
import com.boardgamelang.AST.aexp.AexpNode;
import com.boardgamelang.AST.bexp.*;
import com.boardgamelang.AST.aexp.CountNode;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.EqualityNode;
import com.boardgamelang.AST.bexp.NotNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.gamerule.*;
import com.boardgamelang.AST.pos.OffsetNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.AST.pos.PositionRefNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.PlacePieceAtNode;
import com.boardgamelang.AST.stmt.StmtNode;
import com.boardgamelang.AST.strexp.PieceNode;
import com.boardgamelang.AST.strexp.StrexpNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeChecker {
    private final Set<String> declaredPieces = new HashSet<>();
    private final Map<String, String> pieceToPlayer = new HashMap<>();
    private boolean winWhenPositionsDeclared = false;
    private boolean drawWhenGlobalDeclared = false;
    private boolean gamerulesPositionPieceDeclared = false;
    public enum Type { INT, STRING, POS }


    public void check(ProgramNode program) {
        checkDef(program);
        collectDeclaredPieces(program);
        checkGameRules(program);
        checkStmts(program);
    }

    private void checkDef(ProgramNode program) {
        if (program.defnode instanceof BoardNode board) {
            checkBoard(board);
        }
    }

    private void checkBoard(BoardNode board) {
        if (!(board.pos instanceof PositionNode pos)) {
            throw new TypeException("Board dimensions must be integer literals");
        }
        if (!(pos.x >= 1) || !(pos.y >= 1)) {
            throw new TypeException("Board dimensions must be positive and above 0, got (" + pos.x + ", " + pos.y + ")");
        }
    }

    private void collectDeclaredPieces(ProgramNode program) {
        for (GameRuleNode gameRule : program.gameRuleNodes) {
            if (gameRule instanceof PlayerHasPieceNode p) {
                declaredPieces.add(p.pieceIdent);
            }
        }
    }

    private void checkGameRules(ProgramNode program) {
        for (GameRuleNode gameRule : program.gameRuleNodes) {
            checkGameRule(gameRule);
        }
    }

    private void checkGameRule(GameRuleNode gameRule) {
        switch (gameRule) {
            case PlayerHasPieceNode p -> checkPieceOwnership(p);
            case WinWhenPositionsNode w -> checkWinWhenPositions(w);
            case DrawWhenGlobalNode d -> checkDrawWhenGlobal(d);
            case GamerulesPositionPieceNode g -> checkGamerulesPositionPiece(g);
            default -> {}
        }
    }

    private void checkStmts(ProgramNode program) {
        for (StmtNode stmt : program.stmtNodes) {
            checkStmt(stmt);
        }
    }

    private void checkStmt(StmtNode stmt) {
        switch (stmt) {
            case PlacePieceAtNode p -> checkPlacePieceAt(p);
            case AssertNode a -> checkBexp(a.bexp);
            default -> {}
        }
    }
    
    private void checkBexp(BexpNode bexp) {
        switch (bexp) {
            case EqualityNode e -> checkEqualityNode(e);
            case NotNode n -> checkNotNode(n);
            case AndNode a -> checkAndNode(a);
            case OrNode o -> checkOrNode(o);
            case OccupiedNode o -> checkOccupiedNode(o);
            default -> {throw new TypeException("Invalid bexp");}
        }
    }

    private void checkAndNode(AndNode andNode) {
        checkBexp(andNode.left);
        checkBexp(andNode.right);
    }

    private void checkOrNode(OrNode orNode) {
        checkBexp(orNode.left);
        checkBexp(orNode.right);
    }

    private void checkOccupiedNode(OccupiedNode o) {
        checkPos(o.pos);
    }

    private void checkStrexp(StrexpNode strexp) {
        switch (strexp) {
            case PieceNode p -> checkPieceNode(p);
            default -> throw new TypeException("Invalid strexp: " + strexp.getClass().getSimpleName());
        }
    }

    private void checkPieceNode(PieceNode p) {
        checkPos(p.pos);
    }

    private void checkPositionNode(PositionNode p) {
        if (!(p.x >= 0 || p.y >= 0))
            throw new TypeException("Position must be positive or 0, got (" + p.x + ", " + p.y + ")");
    }

    private void checkPos(PosNode pos) {
        switch (pos) {
            case PositionNode p -> checkPositionNode(p);
            case OffsetNode ignored -> {}
            case PositionRefNode ignored -> {}
            default -> throw new TypeException("Invalid pos: " + pos.getClass().getSimpleName());
        }
    }

    private void checkAexp(AexpNode aexp) {
        switch (aexp) {
            case CountNode c -> checkCountNode(c);
            default -> {}
        }
    }

    private void checkCountNode(CountNode countNode) {
        if (!declaredPieces.contains(countNode.ident)) {
            throw new TypeException("Cannot count undeclared piece: '" + countNode.ident + "'");
        }
    }

    private void checkWinWhenPositions(WinWhenPositionsNode w) {
        if (winWhenPositionsDeclared) {
            throw new TypeException("WinWhenPositionsGameRule already defined, redefine WinWhenPositions to add more win conditions");
        }
        winWhenPositionsDeclared = true;
        checkBexp(w.bexp);
    }

    private void checkDrawWhenGlobal(DrawWhenGlobalNode d) {
        if (drawWhenGlobalDeclared) {
            throw new TypeException("DrawWhenGlobalGameRule already defined, redefine DrawWhenGlobal to add more draw conditions");
        }
        drawWhenGlobalDeclared = true;
        checkBexp(d.bexp);
    }

    private void checkGamerulesPositionPiece(GamerulesPositionPieceNode g) {
        if(gamerulesPositionPieceDeclared){
            throw new TypeException("GamerulesPositionPiece already defined, redefine GamerulesPositionPiece to add more game rules.");
        }
        gamerulesPositionPieceDeclared = true;
        checkBexp(g.bexp);
    }

    private void checkPlacePieceAt(PlacePieceAtNode node) {
        if (!declaredPieces.contains(node.ident)) {
            throw new TypeException("Undeclared piece: '" + node.ident + "'");
        }
    }

    private void checkPieceOwnership(PlayerHasPieceNode node) {
        if (pieceToPlayer.containsKey(node.pieceIdent) &&
                !pieceToPlayer.get(node.pieceIdent).equals(node.playerIdent)) {
            throw new TypeException("Piece '" + node.pieceIdent + "' is already assigned to player '" + pieceToPlayer.get(node.pieceIdent) + "'");
        }
        pieceToPlayer.put(node.pieceIdent, node.playerIdent);
    }

    private Type getType(Node node) {
        return switch (node) {
            case AexpNode a -> Type.INT;
            case StrexpNode s -> Type.STRING;
            case PosNode p -> Type.POS;
            default -> throw new TypeException("Invalid type in ==: " + node.getClass().getSimpleName());
        };
    }

    private void checkEqualityNode(EqualityNode node) {
        Type leftType = getType(node.left);
        Type rightType = getType(node.right);
        if (leftType != rightType) {
            throw new TypeException("Type mismatch in ==: cannot compare " + leftType + " with " + rightType);
        }
        if (node.left  instanceof AexpNode l) checkAexp(l);
        if (node.right instanceof AexpNode r) checkAexp(r);
        if (node.left  instanceof StrexpNode l) checkStrexp(l);
        if (node.right instanceof StrexpNode r) checkStrexp(r);
    }

    private void checkNotNode(NotNode node) {
        // if there is no EqualityNodes inside, checkBexp hits the default and does nothing because there is no types to mismatch.
        // = checkBexp is called with the child (rn only EqualityNodes)
        checkBexp(node.b);
    }
}
