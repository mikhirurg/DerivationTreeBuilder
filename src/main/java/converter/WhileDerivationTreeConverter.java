package converter;

import derivation.rules.whilelang.*;
import parser.syntax.whilelang.arithmeticexp.*;
import parser.syntax.whilelang.booleanexp.*;
import parser.syntax.whilelang.statements.*;
import states.WhileState;

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

    void processWhileState(WhileState state);
}
