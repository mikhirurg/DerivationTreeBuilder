package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

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

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processAnd(this);
    }
}
