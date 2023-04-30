package parser.syntax.whilelang.arithmeticexp;

import states.WhileState;

public class WhileConst implements WhileArithmeticExpression{
    private final WhileNumber number;

    public WhileConst(String number) {
        this.number = new WhileNumber(number);
    }

    public WhileNumber getNumber() {
        return number;
    }

    @Override
    public String textRepresentation() {
        return String.valueOf(number.getValue());
    }

    @Override
    public int evaluate(WhileState state) {
        return number.getValue();
    }
}
