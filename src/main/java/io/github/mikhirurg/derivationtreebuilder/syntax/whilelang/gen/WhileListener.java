package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.gen;// Generated from C:/Users/mikha/IdeaProjects/DerivationTreeBuilder/src/main/antlr4\While.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WhileParser}.
 */
public interface WhileListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WhileParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(WhileParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link WhileParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(WhileParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link WhileParser#stm}.
	 * @param ctx the parse tree
	 */
	void enterStm(WhileParser.StmContext ctx);
	/**
	 * Exit a parse tree produced by {@link WhileParser#stm}.
	 * @param ctx the parse tree
	 */
	void exitStm(WhileParser.StmContext ctx);
	/**
	 * Enter a parse tree produced by {@link WhileParser#aexp}.
	 * @param ctx the parse tree
	 */
	void enterAexp(WhileParser.AexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link WhileParser#aexp}.
	 * @param ctx the parse tree
	 */
	void exitAexp(WhileParser.AexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link WhileParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(WhileParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link WhileParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(WhileParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link WhileParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(WhileParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link WhileParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(WhileParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link WhileParser#bexp}.
	 * @param ctx the parse tree
	 */
	void enterBexp(WhileParser.BexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link WhileParser#bexp}.
	 * @param ctx the parse tree
	 */
	void exitBexp(WhileParser.BexpContext ctx);
}