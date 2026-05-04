package com.boardgamelang.typechecker;

import com.boardgamelang.AST.gamerule.PlayerHasPieceNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.PlacePieceAtNode;
import com.boardgamelang.AST.stmt.StmtNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeChecker {
    private final Set<String> declaredPieces = new HashSet<>();
    private final Map<String, String> pieceToPlayer = new HashMap<>();

    public void check(ProgramNode program) {
        collectDeclaredPieces(program);
        checkStmts(program);
    }

    private void collectDeclaredPieces(ProgramNode program) {
        for (StmtNode stmt : program.stmtNodes) {
            if (stmt instanceof PlayerHasPieceNode p) {
                declaredPieces.add(p.pieceIdent);
            }
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
            case PlayerHasPieceNode p -> checkPieceOwnership(p);
            default -> {}
        }
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
}
