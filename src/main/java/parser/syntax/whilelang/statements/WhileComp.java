package parser.syntax.whilelang.statements;

import derivation.rules.Derivable;
import derivation.rules.DerivationTreeBuilder;
import derivation.rules.whilelang.WhileCompRule;
import derivation.rules.whilelang.WhileDerivationTreeBuilder;
import states.State;
import states.WhileState;

public class WhileComp implements WhileStatement {

    private final WhileStatement statement1;

    private final WhileStatement statement2;

    public WhileComp(WhileStatement statement1, WhileStatement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    public WhileStatement getStatement1() {
        return statement1;
    }

    public WhileStatement getStatement2() {
        return statement2;
    }

    @Override
    public String textRepresentation() {
        return statement1.textRepresentation() + ";\n" + statement2.textRepresentation();
    }

    @Override
    public void accept(DerivationTreeBuilder builder) {
        ((WhileDerivationTreeBuilder) builder).processComp(this);
    }
}
