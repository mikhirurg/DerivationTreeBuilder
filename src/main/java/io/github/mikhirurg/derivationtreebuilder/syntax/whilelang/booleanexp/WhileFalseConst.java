package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileFalseConst implements WhileBooleanExpression{
    @Override
    public String textRepresentation() {
        return "false";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return false;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processFalseConst(this);
    }
}
