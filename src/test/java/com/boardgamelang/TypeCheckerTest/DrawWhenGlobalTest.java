package com.boardgamelang.TypeCheckerTest;

import com.boardgamelang.AST.AstBuilder;
import com.boardgamelang.AST.program.ProgramNode;
import com.boardgamelang.AstTest.ParseTreeHelper;
import com.boardgamelang.BoardGameLangParser;
import com.boardgamelang.typechecker.TypeChecker;
import com.boardgamelang.typechecker.TypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DrawWhenGlobalTest {
    @Test
    void definingDrawRuleTwiceThrows() {
        String input = "board(3,3); draw when global {occupied((1,1))}; draw when global {occupied((1,1))};";

        BoardGameLangParser parser = ParseTreeHelper.createParser(input);
        AstBuilder builder = new AstBuilder();
        ProgramNode program = (ProgramNode) builder.visit(parser.program());

        // Assert that the typeChecker throws exception, that only one draw when can be declared
        TypeChecker typeChecker = new TypeChecker();
        TypeException exception = assertThrows(TypeException.class, () -> typeChecker.check(program));
        System.out.println(exception.getMessage());
    }
}
