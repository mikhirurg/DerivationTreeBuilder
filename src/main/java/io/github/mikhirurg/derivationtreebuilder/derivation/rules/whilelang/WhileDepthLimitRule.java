package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileDepthLimitRule implements DerivationAxiom {

    private final WhileState state;

    public WhileDepthLimitRule(WhileState state) {
        this.state = state;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processDepthLimit();
    }
}
