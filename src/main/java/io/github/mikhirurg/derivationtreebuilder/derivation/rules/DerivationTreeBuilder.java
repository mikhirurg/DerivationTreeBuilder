package io.github.mikhirurg.derivationtreebuilder.derivation.rules;

import io.github.mikhirurg.derivationtreebuilder.syntax.SyntaxNode;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.State;

public interface DerivationTreeBuilder {

    long DERIVATION_DEPTH_LIMIT = 50;
    DerivationTreeNode buildDerivationTree(SyntaxNode program, State state);

    State getState();

    default long getDerivationDepthLimit() {
        return DERIVATION_DEPTH_LIMIT;
    }
}
