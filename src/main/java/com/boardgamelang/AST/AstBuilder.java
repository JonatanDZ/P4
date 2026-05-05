package com.boardgamelang.AST;

import com.boardgamelang.AST.bexp.*;
import com.boardgamelang.AST.aexp.NumNode;
import com.boardgamelang.AST.bexp.*;
import com.boardgamelang.AST.gamerule.*;
import com.boardgamelang.AST.aexp.CountNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.strexp.PieceNode;
import com.boardgamelang.AST.direction.*;
import com.boardgamelang.AST.pos.OffsetNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.AST.direction.DownNode;
import com.boardgamelang.AST.direction.LeftNode;
import com.boardgamelang.AST.direction.RightNode;
import com.boardgamelang.AST.direction.UpNode;
import com.boardgamelang.AST.pos.PositionRefNode;
import com.boardgamelang.AST.stmt.PlacePieceAtNode;
import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.stmt.AssertNode;
import com.boardgamelang.AST.stmt.StmtNode;
import com.boardgamelang.BoardGameLangBaseVisitor;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.BoardGameLangParser.CompContext;
import com.boardgamelang.BoardGameLangParser.CompRuleContext;
import com.boardgamelang.BoardGameLangParser.GameRuleContext;
import com.boardgamelang.BoardGameLangParser.StmtContext;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends BoardGameLangBaseVisitor<Node> {

    @Override
    public Node visitProgram(BoardGameLangParser.ProgramContext ctx) {
        DefNode def = (DefNode) visit(ctx.def());
        List<GameRuleNode> gameRuleList = new ArrayList<>();
        for (CompRuleContext compRuleCtx : ctx.compRule()) {           // outer: each comp. Has a list of a def and comps, as given in program rule in CG.
            for (GameRuleContext gameRuleCtx : compRuleCtx.gameRule()) {   // inner: each stmt in it. Reads inside the list of comps.
                gameRuleList.add((GameRuleNode) visit(gameRuleCtx));
            }
        }
        List<StmtNode> stmtList = new ArrayList<>();
        for (CompContext compCtx : ctx.comp()) {           // outer: each comp. Has a list of a def and comps, as given in program rule in CG.
            for (StmtContext stmtCtx : compCtx.stmt()) {   // inner: each stmt in it. Reads inside the list of comps.
                stmtList.add((StmtNode) visit(stmtCtx));
            }
        }
        return new ProgramNode(def, stmtList, gameRuleList);
    }

    @Override
    public Node visitBoardDef(BoardGameLangParser.BoardDefContext ctx) {
        PosNode pos = (PosNode) visit(ctx.pos());
        return new BoardNode(pos);
    }

    @Override
    public Node visitPosition(BoardGameLangParser.PositionContext ctx) {
        int x = Integer.parseInt(ctx.NUM(0).getText());
        int y = Integer.parseInt(ctx.NUM(1).getText());
        return new PositionNode(x, y);
    }

    @Override
    public Node visitOccupiedBexp(BoardGameLangParser.OccupiedBexpContext ctx) {
        PosNode pos = (PosNode) visit(ctx.pos());
        return new OccupiedNode(pos);
    }

    @Override
    public Node visitAssertStmt(BoardGameLangParser.AssertStmtContext ctx) {
        String ident = ctx.IDENT().getText();
        BexpNode bexp = (BexpNode) visit(ctx.bexp());
        return new AssertNode(ident, bexp);
    }

    @Override
    public Node visitPlayerHasPieceGameRule(BoardGameLangParser.PlayerHasPieceGameRuleContext ctx) {
        String playerIdent = ctx.IDENT(0).getText();
        int n = Integer.parseInt(ctx.NUM().getText());
        String pieceIdent = ctx.IDENT(1).getText();
        return new PlayerHasPieceNode(playerIdent, n, pieceIdent);
    }

    @Override
    public Node visitDrawWhenGlobalGameRule(BoardGameLangParser.DrawWhenGlobalGameRuleContext ctx){
        BexpNode bexp = (BexpNode) visit(ctx.bexp());
        return new DrawWhenGlobalNode(bexp);
    }

    @Override
    public Node visitPlacePieceAtStmt(BoardGameLangParser.PlacePieceAtStmtContext ctx) {
        String x = ctx.IDENT().getText();
        PosNode pos = (PosNode) visit(ctx.pos());
        return new PlacePieceAtNode(pos, x);
    }

    @Override
    public Node visitOrBexp(BoardGameLangParser.OrBexpContext ctx) {
        BexpNode left = (BexpNode) visit(ctx.bexp(0));
        BexpNode right = (BexpNode) visit(ctx.bexp(1));
        return new OrNode(left, right);
    }

    @Override
    public Node visitAndBexp(BoardGameLangParser.AndBexpContext ctx) {
        BexpNode left = (BexpNode) visit(ctx.bexp(0));
        BexpNode right = (BexpNode) visit(ctx.bexp(1));
        return new AndNode(left, right);
    }

    @Override
    public Node visitNotBexp(BoardGameLangParser.NotBexpContext ctx) {
        BexpNode bexp = (BexpNode) visit(ctx.bexp());
        return new NotNode(bexp);
    }

    @Override
    public Node visitLeftDir(BoardGameLangParser.LeftDirContext ctx) {
        return new LeftNode();
    }

    @Override
    public Node visitRightDir(BoardGameLangParser.RightDirContext ctx) {
        return new RightNode();
    }

    @Override
    public Node visitUpDir(BoardGameLangParser.UpDirContext ctx) {
        return new UpNode();
    }

    @Override
    public Node visitDownDir(BoardGameLangParser.DownDirContext ctx) {
        return new DownNode();
    }

    @Override
    public Node visitNumAexp(BoardGameLangParser.NumAexpContext ctx) {
        int n = Integer.parseInt(ctx.NUM().getText());
        return new NumNode(n);
    }

    @Override
    public Node visitPositionRef(BoardGameLangParser.PositionRefContext ctx) {
        return new PositionRefNode();
    }

    @Override
    public Node visitOffsetPos(BoardGameLangParser.OffsetPosContext ctx) {
        PosNode pos = (PosNode) visit(ctx.pos());
        DirNode dir = (DirNode) visit(ctx.dir());
        int amount = Integer.parseInt(ctx.NUM().getText());
        return new OffsetNode(pos, dir, amount);
    }

    @Override
    public Node visitPieceStrexp(BoardGameLangParser.PieceStrexpContext ctx) {
        PosNode pos = (PosNode) visit(ctx.pos());
        return new PieceNode(pos);
    }

    @Override
    public Node visitGamerulesPositionPieceGameRule(BoardGameLangParser.GamerulesPositionPieceGameRuleContext ctx) {
        BexpNode bexp = (BexpNode) visit(ctx.bexp());

        return new GamerulesPositionPieceNode(bexp);
    }

    @Override
    public Node visitWinWhenPositionsGameRule(BoardGameLangParser.WinWhenPositionsGameRuleContext ctx) {
        BexpNode bexp = (BexpNode) visit(ctx.bexp());
        return new WinWhenPositionsNode(bexp);
    }

    @Override
    public Node visitCountAexp(BoardGameLangParser.CountAexpContext ctx) {
        String pieceIdent = ctx.IDENT().getText();
        return new CountNode(pieceIdent);
    }

    @Override
    public Node visitEqualityBexp(BoardGameLangParser.EqualityBexpContext ctx) {
        Node left = visit(ctx.eqexp(0));
        Node right = visit(ctx.eqexp(1));
        return new EqualityNode(left, right);
    }
}
