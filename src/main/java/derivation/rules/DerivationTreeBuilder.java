package derivation.rules;

import parser.syntax.SyntaxNode;
import states.State;

public interface DerivationTreeBuilder {
    DerivationTreeNode buildDerivationTree(SyntaxNode program, State state);

    State getState();
}
