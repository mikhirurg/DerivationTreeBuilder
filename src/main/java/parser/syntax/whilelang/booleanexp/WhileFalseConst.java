package parser.syntax.whilelang.booleanexp;

import states.WhileState;

public class WhileFalseConst implements WhileBooleanExpression{
    @Override
    public String textRepresentation() {
        return "false";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return false;
    }
}
