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
        String input = "gamerules position piece {occupied((2,5))};";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        GamerulesPositionPieceNode node = (GamerulesPositionPieceNode) builder.visit(parser.gameRule());

        assertInstanceOf(BexpNode.class, node.bexp);
    }
}