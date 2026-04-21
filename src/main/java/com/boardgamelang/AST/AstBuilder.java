package com.boardgamelang.AST;

import com.boardgamelang.BoardGameLangBaseVisitor;
import com.boardgamelang.BoardGameLangParser;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends BoardGameLangBaseVisitor<Node> {

    @Override
    public Node visitBoardDef(BoardGameLangParser.BoardDefContext ctx) {
        int width = Integer.parseInt(ctx.NUM(0).getText());
        int height = Integer.parseInt(ctx.NUM(1).getText());
        return new BoardDecl(width, height);
    }

}
