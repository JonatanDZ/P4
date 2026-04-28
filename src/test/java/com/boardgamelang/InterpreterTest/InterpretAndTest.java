package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.bexp.OrNode;
import com.boardgamelang.AST.pos.PosNode;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterpretAndTest {
    @Test
    void andReturnsTrueWhenBothOperandsAreTrue() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertTrue(result);
    }

    @Test
    void andReturnsFalseWhenLeftOperandIsFalse() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertFalse(result);
    }

    @Test
    void andReturnsFalseWhenRightOperandIsFalse() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertFalse(result);
    }

    @Test
    void andReturnsFalseWhenBothOperandsAreFalse() {
        Interpreter interp = new Interpreter();

        boolean result = interp.execBexp(new AndNode(
                new OccupiedNode(new PosNode(1, 1)),
                new OccupiedNode(new PosNode(2, 2))
        ));

        assertFalse(result);
    }

    @Test
    void andReturnsTrueWhenAllThreeOperandsAreTrue() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(2, 2), "X");
        interp.state.beta.put(new Position(3, 3), "X");

        boolean result = interp.execBexp(new AndNode(
                new AndNode(
                        new OccupiedNode(new PosNode(1, 1)),
                        new OccupiedNode(new PosNode(2, 2))
                ),
                new OccupiedNode(new PosNode(3, 3))
        ));

        assertTrue(result);
    }

    @Test
    void andReturnsFalseWhenOneOfThreeOperandsIsFalse() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(3, 3), "X");

        boolean result = interp.execBexp(new AndNode(
                new AndNode(
                        new OccupiedNode(new PosNode(1, 1)),
                        new OccupiedNode(new PosNode(2, 2))
                ),
                new OccupiedNode(new PosNode(3, 3))
        ));

        assertFalse(result);
    }

    @Test
    void andOrReturnsTrueWhenAndIsTrueOrIsFalse() {
        // (occupied(1,1) and occupied(2,2)) or occupied(3,3)
        // and is true, or is false → true
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new OrNode(
                new AndNode(
                        new OccupiedNode(new PosNode(1, 1)),
                        new OccupiedNode(new PosNode(2, 2))
                ),
                new OccupiedNode(new PosNode(3, 3))
        ));

        assertTrue(result);
    }

    @Test
    void andOrReturnsFalseWhenBothAndAndOrAreFalse() {
        // (occupied(1,1) and occupied(2,2)) or occupied(3,3)
        // and is false, or is false → false
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");

        boolean result = interp.execBexp(new OrNode(
                new AndNode(
                        new OccupiedNode(new PosNode(1, 1)),
                        new OccupiedNode(new PosNode(2, 2))
                ),
                new OccupiedNode(new PosNode(3, 3))
        ));

        assertFalse(result);
    }

    @Test
    void andOrReturnsTrueWhenBothAndAndOrAreTrue() {
        // (occupied(1,1) and occupied(2,2)) or occupied(3,3)
        // and is true, or is true → true
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(2, 2), "X");
        interp.state.beta.put(new Position(3, 3), "X");

        boolean result = interp.execBexp(new OrNode(
                new AndNode(
                        new OccupiedNode(new PosNode(1, 1)),
                        new OccupiedNode(new PosNode(2, 2))
                ),
                new OccupiedNode(new PosNode(3, 3))
        ));

        assertTrue(result);
    }
}
