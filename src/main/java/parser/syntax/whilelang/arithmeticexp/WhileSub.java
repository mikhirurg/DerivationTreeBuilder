package parser.syntax.whilelang.arithmeticexp;

import states.WhileState;

public class WhileSub implements WhileArithmeticExpression{
    private final WhileArithmeticExpression left;
    private final WhileArithmeticExpression right;

    public WhileSub(WhileArithmeticExpression left, WhileArithmeticExpression right) {
        this.left = left;
        this.right = right;
    }

    public WhileArithmeticExpression getLeft() {
        return left;
    }

    public WhileArithmeticExpression getRight() {
        return right;
    }

    @Override
    public String textRepresentation() {
        return "(" + left.textRepresentation() + " - " + right.textRepresentation() + ")";
    }

    @Override
    public int evaluate(WhileState state) {
        return left.evaluate(state) - right.evaluate(state);
    }
}
