package com.boardgamelang.InterpreterTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.aexp.CountNode;
import com.boardgamelang.AST.strexp.PieceNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.interpreter.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpreterCountTest {
    @Test
    public void returnCorrectAmount(){
        Interpreter interp = new Interpreter();
        // Populate beta with pieces
        interp.state.beta.put(new Position(1, 1), "knight");
        interp.state.beta.put(new Position(2, 1), "knight");
        interp.state.beta.put(new Position(1, 2), "knight");
        interp.state.beta.put(new Position(1, 3), "knight");

        String input = "count(knight)";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        CountNode node = (CountNode) builder.visit(parser.aexp());
        long result = interp.execAexp(node);

        // Assert that there exist 4 knights in state beta
        assertEquals(4,result);
    }

    @Test
    public void returnsCorrectWithMultiplePiecesOnBoard(){
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "queen");
        interp.state.beta.put(new Position(2, 1), "knight");
        interp.state.beta.put(new Position(1, 2), "queen");
        interp.state.beta.put(new Position(1, 3), "knight");
        interp.state.beta.put(new Position(1, 6), "queen");
        interp.state.beta.put(new Position(1, 4), "knight");

        String input = "count(queen)";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        CountNode node = (CountNode) builder.visit(parser.aexp());
        long result = interp.execAexp(node);

        assertEquals(3,result);
    }

    @Test
    public void lookForPieceNotOnBoard(){
        Interpreter interp = new Interpreter();
        interp.state.beta.put(new Position(1, 1), "queen");

        String input = "count(knight)";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();

        CountNode node = (CountNode) builder.visit(parser.aexp());
        long result = interp.execAexp(node);

        assertEquals(0,result);
    }
}
