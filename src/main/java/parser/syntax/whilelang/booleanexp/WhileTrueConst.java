package parser.syntax.whilelang.booleanexp;

import states.WhileState;

public class WhileTrueConst implements WhileBooleanExpression {
    @Override
    public String textRepresentation() {
        return "true";
    }

    @Override
    public boolean evaluate(WhileState state) {
        return true;
    }
}
