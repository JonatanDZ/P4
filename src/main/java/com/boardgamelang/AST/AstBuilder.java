package com.boardgamelang.AST;

import com.boardgamelang.BoardGameLangBaseVisitor;
import com.boardgamelang.BoardGameLangParser;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends BoardGameLangBaseVisitor<Node> {

    @Override
    public Node visitProgram(BoardGameLangParser.ProgramContext ctx) {
        List<Def> defs = new ArrayList<>();
        defs.add((Def) visit(ctx.def()));

        List<Stmt> stmts = new ArrayList<>();
        for (BoardGameLangParser.CompContext compCtx : ctx.comp()) {
            for (BoardGameLangParser.StmtContext stmtCtx : compCtx.stmt()) {
                stmts.add((Stmt) visit(stmtCtx));
            }
        }

        return new Program(defs, stmts);
    }

    @Override
    public Node visitBoardDef(BoardGameLangParser.BoardDefContext ctx) {
        int width = Integer.parseInt(ctx.NUM(0).getText());
        int height = Integer.parseInt(ctx.NUM(1).getText());
        return new BoardDecl(width, height);
    }

    @Override
    public Node visitPlaceStmt(BoardGameLangParser.PlaceStmtContext ctx) {
        String piece = ctx.IDENT().getText();
        Pos pos = (Pos) visit(ctx.pos());
        return new Place(piece, pos);
    }

    @Override
    public Node visitPosLit(BoardGameLangParser.PosLitContext ctx) {
        int x = Integer.parseInt(ctx.NUM(0).getText());
        int y = Integer.parseInt(ctx.NUM(1).getText());
        return new PosLit(x, y);
    }
}
