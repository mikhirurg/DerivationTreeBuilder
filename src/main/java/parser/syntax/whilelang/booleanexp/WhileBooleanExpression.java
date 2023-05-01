package parser.syntax.whilelang.booleanexp;

import converter.DerivationTreeConverter;
import states.WhileState;

public interface WhileBooleanExpression {
    String textRepresentation();

    boolean evaluate(WhileState state);

    void accept(DerivationTreeConverter converter);
}
