package com.boardgamelang.typechecker;

import com.boardgamelang.AST.Node;
import com.boardgamelang.AST.aexp.AexpNode;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.bexp.EqualityNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.gamerule.GameRuleNode;
import com.boardgamelang.AST.gamerule.DrawWhenGlobalNode;
import com.boardgamelang.AST.gamerule.PlayerHasPieceNode;
import com.boardgamelang.AST.gamerule.WinWhenPositionsNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.PlacePieceAtNode;
import com.boardgamelang.AST.stmt.StmtNode;
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
            default -> {}
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
    }
}
