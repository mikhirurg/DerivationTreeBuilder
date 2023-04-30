package parser.syntax.whilelang.booleanexp;

import states.WhileState;

public interface WhileBooleanExpression {
    String textRepresentation();

    boolean evaluate(WhileState state);
}
