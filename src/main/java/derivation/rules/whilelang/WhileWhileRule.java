package derivation.rules.whilelang;

import parser.syntax.whilelang.statements.WhileWhile;
import states.WhileState;

public abstract class WhileWhileRule extends WhileDerivationRule {

    protected WhileWhile whileStatement;
    public WhileWhileRule(WhileWhile whileStatement, WhileState state) {
        super(state);
        this.whileStatement = whileStatement;
    }
}
