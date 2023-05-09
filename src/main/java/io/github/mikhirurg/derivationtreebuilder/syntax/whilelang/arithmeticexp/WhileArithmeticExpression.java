package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public interface WhileArithmeticExpression {
    String textRepresentation();

    int evaluate(WhileState state);

    void accept(DerivationTreeConverter converter);
}
