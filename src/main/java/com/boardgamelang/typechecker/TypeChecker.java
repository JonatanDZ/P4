package com.boardgamelang.typechecker;

import com.boardgamelang.AST.def.BoardNode;
import com.boardgamelang.AST.def.DefNode;
import com.boardgamelang.AST.program.ProgramNode;

import java.util.ArrayList;
import java.util.List;

public class TypeChecker {
    private final SymbolTable symbolTable = new SymbolTable();
    private final List<String> errors = new ArrayList<>();

    public List<String> check(ProgramNode program) {
        checkDef(program.defnode);
        return errors;
    }

    private void checkDef(DefNode def) {
        if (def instanceof BoardNode board) {
            if (board.width <= 0 || board.height <= 0) {
                errors.add("Board dimensions must be greater than 0, got (" + board.width + ", " + board.height + ")");
            } else {
                symbolTable.boardWidth = board.width;
                symbolTable.boardHeight = board.height;
            }
        }
    }
}
