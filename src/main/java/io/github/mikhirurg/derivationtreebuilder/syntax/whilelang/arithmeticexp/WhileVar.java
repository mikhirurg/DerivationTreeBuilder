package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp;

import io.github.mikhirurg.derivationtreebuilder.converter.DerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.converter.WhileDerivationTreeConverter;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.exceptions.UninitializedVariableException;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public class WhileVar implements WhileArithmeticExpression, Comparable {

    private final String varName;

    public WhileVar(String varName) {
        this.varName = varName;
    }

    public String getVarName() {
        return varName;
    }

    @Override
    public int hashCode() {
        return varName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof WhileVar)) {
            return false;
        }

        WhileVar var = (WhileVar) obj;

        return varName.equals(var.varName);
    }

    @Override
    public String textRepresentation() {
        return varName;
    }

    @Override
    public int evaluate(WhileState state) {
        if (state.contains(new WhileVar(varName))) {
            return state.getIntVal(new WhileVar(varName));
        } else {
            throw new UninitializedVariableException(varName);
        }
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processVar(this);
    }

    @Override
    public int compareTo(Object o) {
        WhileVar other = (WhileVar) o;
        return varName.compareTo(other.getVarName());
    }
}
