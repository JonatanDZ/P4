package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.State;
import com.boardgamelang.typechecker.TypeChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GomokuTest {
    @Test
    void program() {
        String program =
                "board(5,5);" +
                "player Black has 13 piece O;" +
                "player White has 12 piece X;" +
                "gamerules position piece {!occupied(position)};" +
                "win when positions {" +
                // horizontal 4-in-a-row
                // p x x x
                        "piece(position) == piece(offset (position, right, 1)) and piece(position) == piece(offset (position, right, 2)) and piece(position) == piece(offset (position, right, 3)) " +
                        "or " +
                // x p x x
                        "piece(position) == piece(offset (position, left, 1)) and piece(position) == piece(offset (position, right, 1)) and piece(position) == piece(offset (position, right, 2)) " +
                        "or " +
                // x x p x
                        "piece(position) == piece(offset (position, left, 2)) and piece(position) == piece(offset (position, left, 1)) and piece(position) == piece(offset (position, right, 1)) " +
                        "or " +
                // x x x p
                        "piece(position) == piece(offset (position, left, 1)) and piece(position) == piece(offset (position, left, 2)) and piece(position) == piece(offset (position, left, 3)) " +
                        "or " +
                // vertical 4-in-a-row (top → bottom)
                // p x x x
                        "piece(position) == piece(offset (position, down, 1)) and piece(position) == piece(offset (position, down, 2)) and piece(position) == piece(offset (position, down, 3)) " +
                        "or " +
                // x p x x
                        "piece(position) == piece(offset (position, up, 1)) and piece(position) == piece(offset (position, down, 1)) and piece(position) == piece(offset (position, down, 2)) " +
                        "or " +
                // x x p x
                        "piece(position) == piece(offset (position, up, 2)) and piece(position) == piece(offset (position, up, 1)) and piece(position) == piece(offset (position, down, 1)) " +
                        "or " +
                // x x x p
                        "piece(position) == piece(offset (position, up, 1)) and piece(position) == piece(offset (position, up, 2)) and piece(position) == piece(offset (position, up, 3)) " +
                        "or " +
                // diagonal ↘ 4-in-a-row (top-left → bottom-right)
                // p x x x
                        "piece(position) == piece(offset (offset (position, right, 1), down, 1)) and piece(position) == piece(offset (offset (position, right, 2), down, 2)) and piece(position) == piece(offset (offset (position, right, 3), down, 3)) " +
                        "or " +
                // x p x x
                        "piece(position) == piece(offset (offset (position, left, 1), up, 1)) and piece(position) == piece(offset (offset (position, right, 1), down, 1)) and piece(position) == piece(offset (offset (position, right, 2), down, 2)) " +
                        "or " +
                // x x p x
                        "piece(position) == piece(offset (offset (position, left, 2), up, 2)) and piece(position) == piece(offset (offset (position, left, 1), up, 1)) and piece(position) == piece(offset (offset (position, right, 1), down, 1)) " +
                        "or " +
                // x x x p
                        "piece(position) == piece(offset (offset (position, left, 1), up, 1)) and piece(position) == piece(offset (offset (position, left, 2), up, 2)) and piece(position) == piece(offset (offset (position, left, 3), up, 3)) " +
                        "or " +
                // diagonal ↙ 4-in-a-row (top-right → bottom-left)
                // p x x x
                        "piece(position) == piece(offset (offset (position, left, 1), down, 1)) and piece(position) == piece(offset (offset (position, left, 2), down, 2)) and piece(position) == piece(offset (offset (position, left, 3), down, 3)) " +
                        "or " +
                // x p x x
                        "piece(position) == piece(offset (offset (position, right, 1), up, 1)) and piece(position) == piece(offset (offset (position, left, 1), down, 1)) and piece(position) == piece(offset (offset (position, left, 2), down, 2)) " +
                        "or " +
                // x x p x
                        "piece(position) == piece(offset (offset (position, right, 2), up, 2)) and piece(position) == piece(offset (offset (position, right, 1), up, 1)) and piece(position) == piece(offset (offset (position, left, 1), down, 1)) " +
                        "or " +
                // x x x p
                        "piece(position) == piece(offset (offset (position, right, 1), up, 1)) and piece(position) == piece(offset (offset (position, right, 2), up, 2)) and piece(position) == piece(offset (offset (position, right, 3), up, 3))" +
                "};" +

                // Game: White (X) wins on diagonal (1,1)→(4,4). Black (O) goes first.
                "place piece O at (1,2);" +
                "assert BlackMove1 (occupied((1,2)) and count(O) == 1);" +

                "place piece X at (1,1);" +
                "assert WhiteMove1 (occupied((1,1)) and count(X) == 1);" +

                "place piece O at (3,4);" +
                "assert BlackMove2 (occupied((3,4)) and count(O) == 2);" +

                "place piece X at (2,2);" +
                "assert WhiteMove2 (piece((1,1)) == piece((2,2)));" +

                "place piece O at (5,1);" +
                "assert BlackMove3 (occupied((5,1)) and count(O) == 3);" +

                "place piece X at (3,3);" +
                "assert WhiteMove3 (piece((1,1)) == piece((2,2)) and piece((2,2)) == piece((3,3)));" +

                "place piece O at (5,5);" +
                "assert BlackOCount (count(O) == 4);" +

                "place piece X at (4,4);" +
                "assert WhiteWinningMove (piece((1,1)) == piece((4,4)) and piece((2,2)) == piece((3,3)) and piece((3,3)) == piece((4,4)));"
                ;

        BoardGameLangParser parser = ParseTreeHelper.createParser(program);
        ProgramNode ast = (ProgramNode) new AstBuilder().visit(parser.program());
        TypeChecker typeChecker = new TypeChecker();
        assertDoesNotThrow(() -> typeChecker.check(ast));
        State state = assertDoesNotThrow(() -> new Interpreter().run(ast));

        // every assertion in the program is true:
        assertFalse(state.t.containsValue(false), "at least one assertion evaluated to false");

        // every assertion name exists in t
        String[] expected = {
                "BlackMove1", "WhiteMove1", "BlackMove2", "WhiteMove2",
                "BlackMove3", "WhiteMove3", "BlackOCount", "WhiteWinningMove"
        };
        for (String name : expected) {
            assertTrue(state.t.containsKey(name), "expected assertion missing: " + name);
        }

        // diagonal win must have been registered after move 8
        assertEquals("White", state.sigma.get("win"));
    }
}
