package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileConst implements WhileArithmeticExpression{
    private final WhileNumber number;

    public WhileConst(String number) {
        this.number = new WhileNumber(number);
    }

    public WhileNumber getNumber() {
        return number;
    }

    @Override
    public String textRepresentation() {
        return String.valueOf(number.getValue());
    }

    @Override
    public int evaluate(WhileState state) {
        return number.getValue();
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processConst(this);
    }
}
