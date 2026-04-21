package com.boardgamelang.AST;

import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.StmtNode;
import com.boardgamelang.BoardGameLangBaseVisitor;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.BoardGameLangParser.CompContext;
import com.boardgamelang.BoardGameLangParser.StmtContext;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends BoardGameLangBaseVisitor<Node> {

    @Override
    public Node visitProgram(BoardGameLangParser.ProgramContext ctx) {
        List<DefNode> defList = new ArrayList<>();
        defList.add((DefNode) visit(ctx.def()));

        List<StmtNode> stmtList = new ArrayList<>();
        for (CompContext compCtx : ctx.comp()) {           // outer: each comp. Has a list of a def and comps, as given in program rule in CG.
            for (StmtContext stmtCtx : compCtx.stmt()) {   // inner: each stmt in it. Reads inside the list of comps.
                stmtList.add((StmtNode) visit(stmtCtx));
            }
        }
        return new ProgramNode(defList, stmtList);
    }

    @Override
    public Node visitBoardDef(BoardGameLangParser.BoardDefContext ctx) {
        int width = Integer.parseInt(ctx.NUM(0).getText());
        int height = Integer.parseInt(ctx.NUM(1).getText());
        return new BoardNode(width, height);
    }

    @Override
    public Node visitAssertStmt(BoardGameLangParser.AssertStmtContext ctx) {
        String ident = ctx.IDENT().getText();
        Node bexp = visit(ctx.bexp()); // Visits node child bexp
        return new AssertNode(ident, bexp);
    }
}
