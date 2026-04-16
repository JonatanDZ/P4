// Generated from com/boardgamelang/P4Lang.g4 by ANTLR 4.13.2
package com.boardgamelang;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link P4LangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface P4LangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link P4LangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(P4LangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link P4LangParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(P4LangParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link P4LangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(P4LangParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link P4LangParser#bexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBexp(P4LangParser.BexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link P4LangParser#pos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPos(P4LangParser.PosContext ctx);
}