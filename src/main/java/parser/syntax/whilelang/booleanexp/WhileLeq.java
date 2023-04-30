package parser.syntax.whilelang.booleanexp;

import parser.syntax.whilelang.arithmeticexp.WhileArithmeticExpression;
import states.WhileState;

public class WhileLeq implements WhileBooleanExpression {

    private final WhileArithmeticExpression left;

    private final WhileArithmeticExpression right;

    public WhileLeq(WhileArithmeticExpression left, WhileArithmeticExpression right) {
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
        return "(" + left.textRepresentation() + " <= " + right.textRepresentation() + ")";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return left.evaluate(state) <= right.evaluate(state);
    }
}
