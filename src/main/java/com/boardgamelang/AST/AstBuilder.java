package com.boardgamelang.AST;

import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.def.BoardNode;
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
    public Node visitOccupiedBexp(BoardGameLangParser.OccupiedBexpContext ctx) {
        BoardGameLangParser.PosLitContext pos = (BoardGameLangParser.PosLitContext) ctx.pos();
        int x = Integer.parseInt(pos.NUM(0).getText());
        int y = Integer.parseInt(pos.NUM(1).getText());
        return new OccupiedNode(x, y);
    }
}
