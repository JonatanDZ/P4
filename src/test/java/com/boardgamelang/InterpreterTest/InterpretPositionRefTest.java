package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.pos.OffsetNode;
import com.boardgamelang.AST.pos.PositionRefNode;
import com.boardgamelang.AST.strexp.PieceNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InterpretPositionRefTest {
    // PositionRefNode reads sigma[position] and returns it.
    @Test
    void positionRefReturnsBoundPosition() {
        Interpreter interp = new Interpreter();

        // update sigma
        Position sigmaPos = new Position(2,1);
        interp.state.sigma.put("position", sigmaPos);

        String input = "position";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PositionRefNode node = (PositionRefNode) builder.visit(parser.pos());
        Position result = interp.execPos(node);

        assertEquals(sigmaPos, result);
    }

    // PositionRefNode throws when sigma has no "position" binding.
    @Test
    void positionRefThrowsWhenUnbound() {
        Interpreter interp = new Interpreter();

        // no update of sigma
        String input = "position";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        PositionRefNode node = (PositionRefNode) builder.visit(parser.pos());

        assertThrows(RuntimeException.class,
                () -> interp.execPos(node));
    }
}
