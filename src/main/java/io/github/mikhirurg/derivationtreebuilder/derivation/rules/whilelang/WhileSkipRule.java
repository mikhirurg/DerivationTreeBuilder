package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileSkip;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileSkipRule implements DerivationAxiom {

    private final WhileState state;

    private final WhileSkip skip;

    public WhileSkipRule(WhileSkip skip, WhileState state) {
        this.state = state;
        this.skip = skip;
    }

    @Override
    public WhileState getState() {
        return state;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processSkipRule(this);
    }

    public WhileSkip getSkip() {
        return skip;
    }
}
