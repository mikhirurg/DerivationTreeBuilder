package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileWhile;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileWhileFFRule extends WhileWhileRule implements DerivationAxiom {

    public WhileWhileFFRule(WhileWhile whileStatement, WhileState state) {
        super(whileStatement, state);
    }

    @Override
    public WhileState getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processWhileFFRule(this);
    }
}
