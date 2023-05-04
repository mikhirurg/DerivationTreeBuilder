package derivation.rules;

import parser.syntax.SyntaxNode;
import states.State;

public interface DerivationTreeBuilder {

    long DERIVATION_DEPTH_LIMIT = 50;
    DerivationTreeNode buildDerivationTree(SyntaxNode program, State state);

    State getState();

    default long getDerivationDepthLimit() {
        return DERIVATION_DEPTH_LIMIT;
    }
}
