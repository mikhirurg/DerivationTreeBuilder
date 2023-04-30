package parser.syntax.whilelang.statements;

import derivation.rules.Derivable;
import derivation.rules.DerivationTreeBuilder;
import derivation.rules.whilelang.WhileAssignmentRule;
import derivation.rules.whilelang.WhileDerivationTreeBuilder;
import parser.syntax.whilelang.arithmeticexp.WhileArithmeticExpression;
import parser.syntax.whilelang.arithmeticexp.WhileVar;
import states.State;
import states.WhileState;

public class WhileAssignment implements WhileStatement {

    private final WhileVar variable;

    private final WhileArithmeticExpression expression;

    public WhileAssignment(WhileVar var, WhileArithmeticExpression expression) {
        this.variable = var;
        this.expression = expression;
    }

    public WhileVar getVariable() {
        return variable;
    }

    public WhileArithmeticExpression getExpression() {
        return expression;
    }

    @Override
    public String textRepresentation() {
        return variable.textRepresentation() + " := " + expression.textRepresentation();
    }

    @Override
    public void accept(DerivationTreeBuilder builder) {
        ((WhileDerivationTreeBuilder) builder).processAssignment(this);
    }
}
