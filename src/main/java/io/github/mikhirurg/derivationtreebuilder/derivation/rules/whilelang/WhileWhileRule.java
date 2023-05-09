package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileWhile;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public abstract class WhileWhileRule extends WhileDerivationRule {

    protected WhileWhile whileStatement;
    public WhileWhileRule(WhileWhile whileStatement, WhileState state) {
        super(state);
        this.whileStatement = whileStatement;
    }

    public WhileWhile getWhileStatement() {
        return whileStatement;
    }
}
