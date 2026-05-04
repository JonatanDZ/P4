package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.bexp.AndNode;
import com.boardgamelang.AST.bexp.OccupiedNode;
import com.boardgamelang.AST.bexp.OrNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.AST.pos.PositionNode;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterpretOrTest {
    @Test
    void orReturnsTrueWhenBothOperandsAreTrue() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new OrNode(
                new OccupiedNode(new PositionNode(1, 1)),
                new OccupiedNode(new PositionNode(2, 2))
        ));

        assertTrue(result);
    }

    @Test
    void orReturnsFalseWhenBothOperandsAreFalse() {
        Interpreter interp = new Interpreter();

        boolean result = interp.execBexp(new OrNode(
                new OccupiedNode(new PositionNode(1, 1)),
                new OccupiedNode(new PositionNode(2, 2))
        ));

        assertFalse(result);
    }

    @Test
    void orReturnsTrueWhenOneOfThreeOperandsIsTrue() {
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new OrNode(
                new OrNode(
                        new OccupiedNode(new PositionNode(1, 1)),
                        new OccupiedNode(new PositionNode(2, 2))
                ),
                new OccupiedNode(new PositionNode(3, 3))
        ));

        assertTrue(result);
    }

    @Test
    void orReturnsFalseWhenAllThreeOperandsAreFalse() {
        Interpreter interp = new Interpreter();

        boolean result = interp.execBexp(new OrNode(
                new OrNode(
                        new OccupiedNode(new PositionNode(1, 1)),
                        new OccupiedNode(new PositionNode(2, 2))
                ),
                new OccupiedNode(new PositionNode(3, 3))
        ));

        assertFalse(result);
    }

    @Test
    void orAndReturnsTrueWhenOrIsFalseAndIsTrue() {
        // occupied((1,1)) or (occupied((2,2)) and occupied((3,3)))
        // or is false, and is true → true
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(2, 2), "X");
        interp.state.beta.put(new Position(3, 3), "X");

        boolean result = interp.execBexp(new OrNode(
                new OccupiedNode(new PositionNode(1, 1)),
                new AndNode(
                        new OccupiedNode(new PositionNode(2, 2)),
                        new OccupiedNode(new PositionNode(3, 3))
                )
        ));

        assertTrue(result);
    }

    @Test
    void orAndReturnsFalseWhenBothOrAndAndAreFalse() {
        // occupied((1,1)) or (occupied((2,2)) and occupied((3,3)))
        // or is false, and is false → false
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(2, 2), "X");

        boolean result = interp.execBexp(new OrNode(
                new OccupiedNode(new PositionNode(1, 1)),
                new AndNode(
                        new OccupiedNode(new PositionNode(2, 2)),
                        new OccupiedNode(new PositionNode(3, 3))
                )
        ));

        assertFalse(result);
    }

    @Test
    void orAndReturnsTrueWhenBothOrAndAndAreTrue() {
        // occupied((1,1)) or (occupied((2,2)) and occupied((3,3)))
        // or is true, and is true → true
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "X");
        interp.state.beta.put(new Position(2, 2), "X");
        interp.state.beta.put(new Position(3, 3), "X");

        boolean result = interp.execBexp(new OrNode(
                new OccupiedNode(new PositionNode(1, 1)),
                new AndNode(
                        new OccupiedNode(new PositionNode(2, 2)),
                        new OccupiedNode(new PositionNode(3, 3))
                )
        ));

        assertTrue(result);
    }
}
