package converter;

import derivation.rules.DerivationTreeNode;
import derivation.rules.whilelang.*;
import parser.syntax.whilelang.arithmeticexp.*;
import parser.syntax.whilelang.booleanexp.*;
import parser.syntax.whilelang.statements.*;
import states.WhileState;

public class WhileLatexDerivationTreeConverter implements WhileDerivationTreeConverter {
    @Override
    public String convert(DerivationTreeNode node) {
        return null;
    }

    @Override
    public void processDerivationTreeNode(DerivationTreeNode node) {

    }

    @Override
    public void processConst(WhileConst whileConst) {

    }

    @Override
    public void processMult(WhileMult mult) {

    }

    @Override
    public void processPlus(WhilePlus plus) {

    }

    @Override
    public void processSub(WhileSub sub) {

    }

    @Override
    public void processVar(WhileVar var) {

    }

    @Override
    public void processEq(WhileEq eq) {

    }

    @Override
    public void processTrueConst(WhileTrueConst trueConst) {

    }

    @Override
    public void processFalseConst(WhileFalseConst falseConst) {

    }

    @Override
    public void processLeq(WhileLeq leq) {

    }

    @Override
    public void processNot(WhileNot not) {

    }

    @Override
    public void processAnd(WhileAnd and) {

    }

    @Override
    public void processAssignment(WhileAssignment assignment) {

    }

    @Override
    public void processSkip(WhileSkip skip) {

    }

    @Override
    public void processComp(WhileComp comp) {

    }

    @Override
    public void processIf(WhileIf whileIf) {

    }

    @Override
    public void processWhile(WhileWhile whileWhile) {

    }

    @Override
    public void processAssignmentRule(WhileAssignmentRule assignmentRule) {

    }

    @Override
    public void processCompRule(WhileCompRule compRule) {

    }

    @Override
    public void processIfFFRule(WhileIfFFRule ifFFRule) {

    }

    @Override
    public void processIfTTRule(WhileIfTTRule ifTTRule) {

    }

    @Override
    public void processSkipRule(WhileSkipRule skipRule) {

    }

    @Override
    public void processWhileFFRule(WhileWhileFFRule whileFFRule) {

    }

    @Override
    public void processWhileTTRule(WhileWhileTTRule whileTTRule) {

    }

    @Override
    public void processWhileState(WhileState state) {

    }
}
