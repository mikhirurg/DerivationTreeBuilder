package parser.syntax.whilelang.arithmeticexp;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import states.WhileState;

public class WhileMult implements WhileArithmeticExpression {

    private final WhileArithmeticExpression left;
    private final WhileArithmeticExpression right;

    public WhileMult(WhileArithmeticExpression left, WhileArithmeticExpression right) {
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
        return "(" + left.textRepresentation() + " * " + right.textRepresentation() + ")";
    }

    @Override
    public int evaluate(WhileState state) {
        return left.evaluate(state) * right.evaluate(state);
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processMult(this);
    }
}
