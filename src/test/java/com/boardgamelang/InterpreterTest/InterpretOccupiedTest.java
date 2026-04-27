package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterpretOccupiedTest {
    @Test
    void occupiedReturnsTrueWhenPositionInBeta() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        boolean result = interp.execBexp(new OccupiedNode(new PosNode(1, 1)));

        assertTrue(result);
    }

    @Test
    void occupiedReturnsFalseWhenPositionEmpty() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        boolean result = interp.execBexp(new OccupiedNode(new PosNode(2, 2)));

        assertFalse(result);
    }
}
