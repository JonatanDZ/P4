package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.AST.pos.PositionRefNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class VisitPositionRefTest {
    @Test
    void positionKeywordParsesAsPositionRefNode() {
        String input = "position";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PosNode node = (PosNode) builder.visit(parser.pos());

        assertInstanceOf(PositionRefNode.class, node);
    }
}
