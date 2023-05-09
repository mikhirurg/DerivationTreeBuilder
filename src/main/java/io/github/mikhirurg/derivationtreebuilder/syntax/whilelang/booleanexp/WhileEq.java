package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.WhileArithmeticExpression;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileEq implements WhileBooleanExpression {
    private final WhileArithmeticExpression left;
    private final WhileArithmeticExpression right;

    public WhileEq(WhileArithmeticExpression left, WhileArithmeticExpression right) {
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
        return "(" + left.textRepresentation() + " = " + right.textRepresentation() + ")";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return left.evaluate(state) == right.evaluate(state);
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processEq(this);
    }

}
