package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileTrueConst implements WhileBooleanExpression {
    @Override
    public String textRepresentation() {
        return "true";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return true;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processTrueConst(this);
    }
}
