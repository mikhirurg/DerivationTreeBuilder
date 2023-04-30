package parser.syntax.whilelang.arithmeticexp;

import states.WhileState;

public interface WhileArithmeticExpression {
    String textRepresentation();

    int evaluate(WhileState state);
}
