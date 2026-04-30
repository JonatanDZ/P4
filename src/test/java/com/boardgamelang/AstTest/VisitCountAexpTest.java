package com.boardgamelang.AstTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.aexp.CountNode;
import com.boardgamelang.BoardGameLangParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class VisitCountAexpTest {

    @Test
    public void returnsCorrectPieceIdent() {
        String input = "count(knight);";
        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        CountNode node = (CountNode) builder.visit(parser.aexp());
        // assert countNode retrieves the correct information from the construct, in this case "knight"
        assertEquals("knight", node.ident);
    }

}
