package com.boardgamelang.TypeCheckerTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.interpreter.Interpreter;
import com.boardgamelang.typechecker.TypeChecker;
import com.boardgamelang.typechecker.TypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinWhenPositionsTest {

    @Test
    void definingWinRuleTwiceThrows() {
        String input = "board(3,3); win when positions {occupied((1,1))}; win when positions {occupied((2,2))};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        // Assert that the typeChecker throws exception, that only one win when can be declared
        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());
    }
}
