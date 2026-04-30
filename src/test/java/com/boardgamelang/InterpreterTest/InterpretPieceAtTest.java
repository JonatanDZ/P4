package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AST.strexp.PieceNode;
import com.boardgamelang.AST.strexp.StrexpNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import com.boardgamelang.interpreter.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterpretPieceAtTest {
    @Test
    void returnCorrectPiece() {
        Interpreter interp = new Interpreter();
        // populate state beta with positions and pieces
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(1, 2), "Y");

        String input = "piece(1,1);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PieceNode node = (PieceNode) builder.visit(parser.strexp());
        String result = interp.execStrexp(node);

        // Assert that piece(1,1) returns the string X, since beta is populated that way
        assertEquals("X", result);
    }

    @Test
    void returnNullIfPosIsEmpty() {
        Interpreter interp = new Interpreter();

        String input = "piece(1,1);";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PieceNode node = (PieceNode) builder.visit(parser.strexp());
        String result = interp.execStrexp(node);

        // Assert that piece(1,1) returns null since there is no piece at the position
        assertNull(result);
    }


}
