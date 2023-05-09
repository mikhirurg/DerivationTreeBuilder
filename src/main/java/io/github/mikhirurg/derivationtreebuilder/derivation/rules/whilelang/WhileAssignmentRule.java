package io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.WhileAssignment;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileAssignmentRule extends WhileDerivationRule implements DerivationAxiom {

    private final WhileAssignment assignment;

    public WhileAssignmentRule(WhileAssignment assignment, WhileState state) {
        super(state);
        this.assignment = assignment;
    }

    @Override
    public WhileState getState() {
        return initialState;
    }


    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processAssignmentRule(this);
    }

    public WhileAssignment getAssignment() {
        return assignment;
    }
}
