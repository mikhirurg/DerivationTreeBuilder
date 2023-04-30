package derivation.rules.whilelang;

import parser.syntax.whilelang.statements.WhileAssignment;
import states.WhileState;

public class WhileAssignmentRule extends WhileDerivationRule implements DerivationAxiom {

    private final WhileAssignment assignment;
    public WhileAssignmentRule(WhileAssignment assignment, WhileState state) {
        super(state);
        this.assignment = assignment;
    }

    @Override
    public WhileState getState() {
        return state;
    }

    public WhileAssignment getAssignment() {
        return assignment;
    }
}
