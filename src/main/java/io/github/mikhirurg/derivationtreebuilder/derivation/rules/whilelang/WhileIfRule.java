package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileIf;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public abstract class WhileIfRule extends WhileDerivationRule {

    protected WhileIf ifStatement;
    public WhileIfRule(WhileIf ifStatement, WhileState state) {
        super(state);
        this.ifStatement = ifStatement;
    }

    public WhileIf getIfStatement() {
        return ifStatement;
    }
}
