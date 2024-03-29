package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang;

import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.gen.WhileBaseListener;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.gen.WhileParser;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.*;

import java.util.Stack;
import java.util.regex.Pattern;

public class WhileListener extends WhileBaseListener {
    private final Stack<WhileArithmeticExpression> arithmeticStack;

    private final Stack<WhileBooleanExpression> booleanStack;

    private final Stack<WhileStatement> statementStack;

    private static final Pattern VAR_PATTERN = Pattern.compile("[a-zA-Z]+");

    private static final Pattern INT_PATTERN = Pattern.compile("[0-9]+");

    private WhileStatement program;

    public WhileListener() {
        this.arithmeticStack = new Stack<>();
        this.booleanStack = new Stack<>();
        this.statementStack = new Stack<>();
    }

    public WhileStatement getProgram() {
        return program;
    }

    @Override
    public void exitProg(WhileParser.ProgContext ctx) {
        super.exitProg(ctx);

        this.program = statementStack.pop();
    }

    @Override
    public void exitStm(WhileParser.StmContext ctx) {
        super.exitStm(ctx);
        if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals(";")) {
            WhileStatement s2 = statementStack.pop();
            WhileStatement s1 = statementStack.pop();
            statementStack.add(new WhileComp(s1, s2));
        } else if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals(":=")) {
            WhileArithmeticExpression expression = arithmeticStack.pop();

            WhileVar var;
            if (arithmeticStack.empty()) {
                var = new WhileVar(ctx.children.get(0).getText());
            } else {
                var = (WhileVar) arithmeticStack.pop();
            }

            statementStack.add(new WhileAssignment(var, expression));
        } else if (ctx.children.size() == 1 && ctx.children.get(0).getText().equals("skip")) {
            statementStack.add(new WhileSkip());
        } else if (ctx.children.size() == 6 && ctx.children.get(0).getText().equals("if")) {
            WhileBooleanExpression expression = booleanStack.pop();
            WhileStatement s2 = statementStack.pop();
            WhileStatement s1 = statementStack.pop();
            statementStack.add(new WhileIf(expression, s1, s2));
        } else if (ctx.children.size() == 4 && ctx.children.get(0).getText().equals("while")) {
            WhileBooleanExpression expression = booleanStack.pop();
            WhileStatement statement = statementStack.pop();
            statementStack.add(new WhileWhile(expression, statement));
        }
    }

    @Override
    public void exitAexp(WhileParser.AexpContext ctx) {
        super.exitAexp(ctx);
        if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals("+")) {
            WhileArithmeticExpression right = arithmeticStack.pop();
            WhileArithmeticExpression left = arithmeticStack.pop();
            arithmeticStack.add(new WhilePlus(left, right));
        } else if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals("-")) {
            WhileArithmeticExpression right = arithmeticStack.pop();
            WhileArithmeticExpression left = arithmeticStack.pop();
            arithmeticStack.add(new WhileSub(left, right));
        }
    }

    @Override
    public void exitMultExpr(WhileParser.MultExprContext ctx) {
        super.exitMultExpr(ctx);
        if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals("*")) {
            WhileArithmeticExpression right = arithmeticStack.pop();
            WhileArithmeticExpression left = arithmeticStack.pop();
            arithmeticStack.add(new WhileMult(left, right));
        }
    }

    @Override
    public void exitAtom(WhileParser.AtomContext ctx) {
        super.exitAtom(ctx);
        if (ctx.children.size() == 1) {
            if (VAR_PATTERN.matcher(ctx.children.get(0).getText()).matches()) {
                arithmeticStack.add(new WhileVar(ctx.children.get(0).getText()));
            } else if (INT_PATTERN.matcher(ctx.children.get(0).getText()).matches()) {
                arithmeticStack.add(new WhileConst(ctx.children.get(0).getText()));
            }
        }
    }

    @Override
    public void exitBexp(WhileParser.BexpContext ctx) {
        super.exitBexp(ctx);
        if (ctx.children.size() == 1 && ctx.children.get(0).getText().equals("true")) {
            booleanStack.add(new WhileTrueConst());
        } else if (ctx.children.size() == 1 && ctx.children.get(0).getText().equals("false")) {
            booleanStack.add(new WhileFalseConst());
        } else if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals("=")) {
            WhileArithmeticExpression right = arithmeticStack.pop();
            WhileArithmeticExpression left = arithmeticStack.pop();
            booleanStack.add(new WhileEq(left, right));
        } else if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals("<=")) {
            WhileArithmeticExpression right = arithmeticStack.pop();
            WhileArithmeticExpression left = arithmeticStack.pop();
            booleanStack.add(new WhileLeq(left, right));
        } else if (ctx.children.size() == 4 && ctx.children.get(0).getText().equals("not")) {
            WhileBooleanExpression expression = booleanStack.pop();
            booleanStack.add(new WhileNot(expression));
        } else if (ctx.children.size() == 3 && ctx.children.get(1).getText().equals("and")) {
            WhileBooleanExpression right = booleanStack.pop();
            WhileBooleanExpression left = booleanStack.pop();
            booleanStack.add(new WhileAnd(left, right));
        }
    }
}
