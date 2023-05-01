package parser.syntax.whilelang.arithmeticexp;

import converter.DerivationTreeConverter;
import states.WhileState;

public interface WhileArithmeticExpression {
    String textRepresentation();

    int evaluate(WhileState state);

    void accept(DerivationTreeConverter converter);
}
