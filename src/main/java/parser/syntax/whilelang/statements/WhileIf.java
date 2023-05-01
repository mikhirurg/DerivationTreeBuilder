package parser.syntax.whilelang.statements;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import derivation.rules.DerivationTreeBuilder;
import derivation.rules.whilelang.WhileDerivationTreeBuilder;
import parser.syntax.whilelang.booleanexp.WhileBooleanExpression;

public class WhileIf implements WhileStatement {

    private final WhileBooleanExpression condition;

    private final WhileStatement statement1;

    private final WhileStatement statement2;

    public WhileIf(WhileBooleanExpression condition, WhileStatement statement1, WhileStatement statement2) {
        this.condition = condition;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    public WhileBooleanExpression getCondition() {
        return condition;
    }

    public WhileStatement getStatement1() {
        return statement1;
    }

    public WhileStatement getStatement2() {
        return statement2;
    }

    @Override
    public String textRepresentation() {
        return "if " + condition + " then " + statement1.textRepresentation() + " else " + statement2.textRepresentation();
    }

    @Override
    public void accept(DerivationTreeBuilder builder) {
        ((WhileDerivationTreeBuilder) builder).processIf(this);
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processIf(this);
    }
}
