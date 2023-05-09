package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileWhile;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileWhileTTRule extends WhileWhileRule implements DerivationRule {
    public WhileWhileTTRule(WhileWhile whileStatement, WhileState state) {
        super(whileStatement, state);
    }

    @Override
    public State getState() {
        return initialState;
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processWhileTTRule(this);
    }
}
