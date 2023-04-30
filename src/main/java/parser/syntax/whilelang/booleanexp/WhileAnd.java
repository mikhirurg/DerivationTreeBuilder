package parser.syntax.whilelang.booleanexp;

import states.WhileState;

public class WhileAnd implements WhileBooleanExpression {
    private final WhileBooleanExpression left;
    private final WhileBooleanExpression right;

    public WhileAnd(WhileBooleanExpression left, WhileBooleanExpression right) {
        this.left = left;
        this.right = right;
    }

    public WhileBooleanExpression getLeft() {
        return left;
    }

    public WhileBooleanExpression getRight() {
        return right;
    }

    @Override
    public String textRepresentation() {
        return "(" + left.textRepresentation() + " ^ " + right.textRepresentation() + ")";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return left.evaluate(state) && right.evaluate(state);
    }
}
