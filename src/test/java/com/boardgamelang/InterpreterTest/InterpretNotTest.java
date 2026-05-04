package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.bexp.NotNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.bexp.OrNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InterpretNotTest {

    @Test
    void NotReturnsTrue() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        boolean result = interp.execBexp(new NotNode(
                new OccupiedNode(new PositionNode(2, 1))
        ));

        assertTrue(result);
    }

    @Test
    void NotReturnsFalse() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        boolean result = interp.execBexp(new NotNode(
                new OccupiedNode(new PositionNode(1, 1))
        ));

        assertFalse(result);
    }
}
