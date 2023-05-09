package io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.exceptions;

public class UninitializedVariableException extends WhileArithmeticException {
    public UninitializedVariableException(String var) {
        super("Variable " + var + " is not initialized!");
    }
}
