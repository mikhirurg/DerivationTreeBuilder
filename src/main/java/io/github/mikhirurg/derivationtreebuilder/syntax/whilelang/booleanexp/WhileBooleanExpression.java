package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public interface WhileBooleanExpression {
    String textRepresentation();

    boolean evaluate(WhileState state);

    void accept(DerivationTreeConverter converter);
}
