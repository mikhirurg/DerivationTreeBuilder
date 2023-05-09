package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileIf;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileIfTTRule extends WhileIfRule implements DerivationRule {

    public WhileIfTTRule(WhileIf ifStatement, WhileState s) {
        super(ifStatement, s);
    }

    @Override
    public State getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processIfTTRule(this);
    }
}
