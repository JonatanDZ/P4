package com.boardgamelang.AST;

import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.BoardGameLangBaseVisitor;
import com.boardgamelang.BoardGameLangParser;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends BoardGameLangBaseVisitor<Node> {
    @Override
    public Node visitBoardDef(BoardGameLangParser.BoardDefContext ctx) {
        int width = Integer.parseInt(ctx.NUM(0).getText());
        int height = Integer.parseInt(ctx.NUM(1).getText());
        return new BoardNode(width, height);
    }

    @Override
    public Node visitPosLit(BoardGameLangParser.PosLitContext ctx) {
        int x = Integer.parseInt(ctx.NUM(0).getText());
        int y = Integer.parseInt(ctx.NUM(1).getText());
        return new PosNode(x, y);
    }

    @Override
    public Node visitOccupiedBexp(BoardGameLangParser.OccupiedBexpContext ctx) {
        PosNode pos = (PosNode) visit(ctx.pos());
        return new OccupiedNode(pos);
    }

    @Override
    public Node visitAssertStmt(BoardGameLangParser.AssertStmtContext ctx) {
        String ident = ctx.IDENT().getText();
        Node bexp = visit(ctx.bexp()); // Visits node child bexp
        return new AssertNode(ident, bexp);
    }
}
