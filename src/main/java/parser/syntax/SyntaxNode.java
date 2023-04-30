package parser.syntax;

import derivation.rules.Derivable;
import derivation.rules.DerivationTreeBuilder;
import states.State;

public interface SyntaxNode {
    String textRepresentation();

    void accept(DerivationTreeBuilder builder);
}
