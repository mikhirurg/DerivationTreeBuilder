package derivation.rules.whilelang;

import parser.syntax.whilelang.statements.WhileIf;
import states.WhileState;

public abstract class WhileIfRule extends WhileDerivationRule {

    protected WhileIf ifStatement;
    public WhileIfRule(WhileIf ifStatement, WhileState state) {
        super(state);
        this.ifStatement = ifStatement;
    }

    public WhileIf getIfStatement() {
        return ifStatement;
    }
}
