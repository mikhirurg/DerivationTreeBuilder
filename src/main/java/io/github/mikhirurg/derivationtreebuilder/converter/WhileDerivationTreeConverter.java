package io.github.mikhirurg.derivationtreebuilder.converter;

import io.github.mikhirurg.derivationtreebuilder.derivation.rules.whilelang.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.arithmeticexp.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.booleanexp.*;
import io.github.mikhirurg.derivationtreebuilder.syntax.whilelang.statements.*;
import io.github.mikhirurg.derivationtreebuilder.derivation.rules.states.WhileState;

public interface WhileDerivationTreeConverter extends DerivationTreeConverter {
    void processConst(WhileConst whileConst);

    void processMult(WhileMult mult);

    void processPlus(WhilePlus plus);

    void processSub(WhileSub sub);

    void processVar(WhileVar var);

    void processEq(WhileEq eq);

    void processTrueConst(WhileTrueConst trueConst);

    void processFalseConst(WhileFalseConst falseConst);

    void processLeq(WhileLeq leq);

    void processNot(WhileNot not);

    void processAnd(WhileAnd and);

    void processAssignment(WhileAssignment assignment);

    void processSkip(WhileSkip skip);

    void processComp(WhileComp comp);

    void processIf(WhileIf whileIf);

    void processWhile(WhileWhile whileWhile);

    void processAssignmentRule(WhileAssignmentRule assignmentRule);

    void processCompRule(WhileCompRule compRule);

    void processIfFFRule(WhileIfFFRule ifFFRule);

    void processIfTTRule(WhileIfTTRule ifTTRule);

    void processSkipRule(WhileSkipRule skipRule);

    void processWhileFFRule(WhileWhileFFRule whileFFRule);

    void processWhileTTRule(WhileWhileTTRule whileTTRule);

    void processDepthLimit();

    void processWhileState(WhileState state);
}
