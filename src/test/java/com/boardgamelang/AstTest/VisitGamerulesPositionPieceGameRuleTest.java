package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.BexpNode;
import com.boardgamelang.AST.gamerule.GamerulesPositionPieceNode;
import com.boardgamelang.BoardGameLangParser;
import org.antlr.v4.runtime.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VisitGamerulesPositionPieceGameRuleTest {

    // Test to check the entered value is a bexp
    @Test
    void visitGamerulesExpectsBexp() {
        String input = "gamerules position piece {occupied(2,5)};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        GamerulesPositionPieceNode node = (GamerulesPositionPieceNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }
    // Test to check the entered value is a bexp
    @Test
    void visitGamerulesExpectsOccupiedAndOccupied() {
        String input = "gamerules position piece {occupied(2,5) and occupied(3,5)};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        GamerulesPositionPieceNode node = (GamerulesPositionPieceNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }

    @Test
    void visitGamerulesExpectsOccupiedOrOccupied() {
        String input = "gamerules position piece {occupied(1,7) or occupied(4,9)};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        GamerulesPositionPieceNode node = (GamerulesPositionPieceNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }

    @Test
    void visitGamerulesExpectsOccupiedAndOccupiedOrOccupied() {
        String input = "gamerules position piece {occupied(1,7) and occupied(3,7) or occupied(4,9)};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        GamerulesPositionPieceNode node = (GamerulesPositionPieceNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }
}
