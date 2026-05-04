package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.gamerule.WinWhenPositionsNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


public class VisitWinWhenPositionsGameRuleTest {
    @Test
    public void visitWinWhenPositionsGameRuleReturnsPosAndBexp () {
        String input = "win when positions {occupied((3,2)) and occupied((3,1))}";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        WinWhenPositionsNode node = (WinWhenPositionsNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }

    @Test
    public void visitWinWhenPositionsAcceptsSingleOccupied() {
        String input = "win when positions {occupied((3,2))}";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        WinWhenPositionsNode node = (WinWhenPositionsNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }

    @Test
    public void visitWinWhenPositionsAcceptsMultipleAndsSeperatedByOrExpression() {
        String input = "win when positions {occupied((2,1)) and occupied((223,23)) or occupied((323,232)) and occupied((323,132))}";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        WinWhenPositionsNode node = (WinWhenPositionsNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }
}
