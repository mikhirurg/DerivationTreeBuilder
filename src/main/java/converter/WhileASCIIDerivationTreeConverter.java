package converter;

import derivation.rules.DerivationTreeNode;
import derivation.rules.whilelang.*;
import parser.syntax.whilelang.arithmeticexp.*;
import parser.syntax.whilelang.booleanexp.*;
import parser.syntax.whilelang.statements.*;
import states.WhileState;

import java.util.*;

public class WhileASCIIDerivationTreeConverter implements WhileDerivationTreeConverter {

    private final StringBuilder currentBuilder;
    private boolean isExplicitState;

    public WhileASCIIDerivationTreeConverter(boolean explicitState) {
        this.currentBuilder = new StringBuilder();
        this.isExplicitState = explicitState;
    }

    public WhileASCIIDerivationTreeConverter() {
        this(true);
    }

    public static class Pair<K, V> {
        private final K first;
        private final V second;

        public static <K, V> Pair<K, V> of(K first, V second) {
            return new Pair<>(first, second);
        }

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }

    private static class TextTreeNode {
        private String text;
        private final List<TextTreeNode> children;

        private TextTreeNode parent;

        private TextTreeNode previous;

        private int layer;

        public TextTreeNode() {
            children = new ArrayList<>();
        }

        public List<TextTreeNode> getChildren() {
            return children;
        }

        public void addChild(TextTreeNode textTreeNode) {
            children.add(textTreeNode);
        }

        public int getWidth() {
            return Math.max(text.length(), children.stream().mapToInt(TextTreeNode::getWidth).reduce(Integer::sum).orElse(0));
        }

        public int getPositionInLayer() {
            if (previous == null) {
                if (parent != null) {
                    return parent.getPositionInLayer();
                } else {
                    return 0;
                }
            }
            return previous.getPositionInLayer() + previous.getWidth();
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public int getLayer() {
            return layer;
        }

        public void setLayer(int layer) {
            this.layer = layer;
        }

        public TextTreeNode getParent() {
            return parent;
        }

        public void setParent(TextTreeNode parent) {
            this.parent = parent;
        }

        public TextTreeNode getPrevious() {
            return previous;
        }

        public void setPrevious(TextTreeNode previous) {
            this.previous = previous;
        }
    }

    @Override
    public String convert(DerivationTreeNode node) {

        Queue<Pair<Integer, DerivationTreeNode>> queue = new LinkedList<>();
        queue.add(Pair.of(0, node));

        Map<DerivationTreeNode, TextTreeNode> parent = new HashMap<>();

        TextTreeNode base = new TextTreeNode();
        TextTreeNode currentNode = base;

        int maxLayer = 0;
        TextTreeNode prevNode = null;

        while (queue.size() > 0) {
            Pair<Integer, DerivationTreeNode> treeNode = queue.poll();

            if (treeNode.getSecond() == null) {
                prevNode = null;
            } else {
                processDerivationTreeNode(treeNode.getSecond());
                String rule = currentBuilder.toString();

                currentNode.setText(rule);
                currentNode.setLayer(treeNode.getFirst());
                currentNode.setParent(parent.get(treeNode.getSecond()));
                if (prevNode != currentNode.getParent()) {
                    currentNode.setPrevious(prevNode);
                }

                prevNode = currentNode;

                maxLayer = Math.max(maxLayer, treeNode.getFirst());

                if (parent.containsKey(treeNode.getSecond())) {
                    parent.get(treeNode.getSecond()).addChild(currentNode);
                }

                for (DerivationTreeNode childNode : treeNode.getSecond().getChildren()) {
                    queue.add(Pair.of(treeNode.getFirst() + 1, childNode));
                    parent.put(childNode, currentNode);
                }
                queue.add(Pair.of(-1, null));

                currentNode = new TextTreeNode();
            }
        }

        ArrayList<StringBuilder> lines = new ArrayList<>();

        for (int i = 0; i <= 2*maxLayer + 1; i++) {
            lines.add(new StringBuilder());
        }

        Queue<TextTreeNode> textTreeNodes = new LinkedList<>();
        textTreeNodes.add(base);

        while (textTreeNodes.size() > 0) {
            TextTreeNode treeNode = textTreeNodes.poll();

            String transition = treeNode.getText().split("\n")[0];
            String rule = treeNode.getText().split("\n")[1];

            lines.get(treeNode.getLayer() * 2)
                    .append(" ".repeat(Math.max(0, treeNode.getPositionInLayer() - lines.get(treeNode.getLayer() * 2).length() + 1)))
                    .append(transition);

            String ruleDelimiter = treeNode.getChildren().size() == 0 ? " " : "-";
            lines.get(treeNode.getLayer() * 2 + 1)
                    .append(" ".repeat(Math.max(0, treeNode.getPositionInLayer() - lines.get(treeNode.getLayer() * 2 + 1).length() + 1)))
                    .append(ruleDelimiter.repeat(treeNode.getWidth() - rule.length())).append(rule);

            textTreeNodes.addAll(treeNode.getChildren());
        }

        StringBuilder builder = new StringBuilder();

        for (int i = lines.size() - 1; i >= 0; i--) {
            builder.append(lines.get(i)).append("\n");
        }

        return builder.toString();
    }

    @Override
    public void processDerivationTreeNode(DerivationTreeNode node) {
        node.getRule().accept(this);
        String rule = currentBuilder.toString().split("\n")[0];
        String statement = currentBuilder.toString().split("\n")[1];

        currentBuilder.setLength(0);
        node.initialState().accept(this);
        String startState = currentBuilder.toString();

        currentBuilder.setLength(0);
        node.finalState().accept(this);
        String finalState = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("<")
                .append(statement)
                .append(", ")
                .append(startState)
                .append("> -> ")
                .append(finalState)
                .append("\n")
                .append(rule);
    }

    @Override
    public void processConst(WhileConst whileConst) {
        currentBuilder.setLength(0);
        currentBuilder.append(whileConst.getNumber().getValue());
    }

    @Override
    public void processMult(WhileMult mult) {
        currentBuilder.setLength(0);

        mult.getLeft().accept(this);
        String left = currentBuilder.toString();

        mult.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" * ")
                .append(right)
                .append(")");
    }

    @Override
    public void processPlus(WhilePlus plus) {
        currentBuilder.setLength(0);

        plus.getLeft().accept(this);
        String left = currentBuilder.toString();

        plus.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" + ")
                .append(right)
                .append(")");
    }

    @Override
    public void processSub(WhileSub sub) {
        currentBuilder.setLength(0);

        sub.getLeft().accept(this);
        String left = currentBuilder.toString();

        sub.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("(")
                .append(left)
                .append(" - ")
                .append(right)
                .append(")");
    }

    @Override
    public void processVar(WhileVar var) {
        currentBuilder.setLength(0);
        currentBuilder.append(var.getVarName());
    }

    @Override
    public void processEq(WhileEq eq) {
        currentBuilder.setLength(0);

        eq.getLeft().accept(this);
        String left = currentBuilder.toString();

        eq.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.append("(")
                .append(left)
                .append(" = ")
                .append(right)
                .append(")");
    }

    @Override
    public void processTrueConst(WhileTrueConst trueConst) {
        currentBuilder.setLength(0);
        currentBuilder.append("true");
    }

    @Override
    public void processFalseConst(WhileFalseConst falseConst) {
        currentBuilder.setLength(0);
        currentBuilder.append("false");
    }

    @Override
    public void processLeq(WhileLeq leq) {
        currentBuilder.setLength(0);

        leq.getLeft().accept(this);
        String left = currentBuilder.toString();

        leq.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.append("(")
                .append(left)
                .append(" <= ")
                .append(right)
                .append(")");
    }

    @Override
    public void processNot(WhileNot not) {
        currentBuilder.setLength(0);
        not.getExpression().accept(this);
        String expressionText = currentBuilder.toString();

        currentBuilder.append("not(").append(expressionText).append(")");
    }

    @Override
    public void processAnd(WhileAnd and) {
        currentBuilder.setLength(0);

        and.getLeft().accept(this);
        String left = currentBuilder.toString();

        and.getRight().accept(this);
        String right = currentBuilder.toString();

        currentBuilder.append("(")
                .append(left)
                .append(" and ")
                .append(right)
                .append(")");
    }

    @Override
    public void processAssignment(WhileAssignment assignment) {
        currentBuilder.setLength(0);

        assignment.getVariable().accept(this);
        String variableText = currentBuilder.toString();

        assignment.getExpression().accept(this);
        String expressionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append(variableText).append(" := ").append(expressionText);
    }

    @Override
    public void processSkip(WhileSkip skip) {
        currentBuilder.setLength(0);

        currentBuilder.append("skip");
    }

    @Override
    public void processComp(WhileComp comp) {
        currentBuilder.setLength(0);

        comp.getStatement1().accept(this);
        String statement1Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        comp.getStatement2().accept(this);
        String statement2Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append(statement1Text).append("; ").append(statement2Text);
    }

    @Override
    public void processIf(WhileIf whileIf) {
        currentBuilder.setLength(0);
        whileIf.getCondition().accept(this);
        String conditionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        whileIf.getStatement1().accept(this);
        String statement1Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        whileIf.getStatement2().accept(this);
        String statement2Text = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("if ").append(conditionText)
                .append(" then")
                .append("(")
                .append(statement1Text)
                .append(") ")
                .append("else")
                .append(" (")
                .append(statement2Text)
                .append(")");
    }

    @Override
    public void processWhile(WhileWhile whileWhile) {
        currentBuilder.setLength(0);
        whileWhile.getCondition().accept(this);
        String conditionText = currentBuilder.toString();

        currentBuilder.setLength(0);
        whileWhile.getStatement().accept(this);
        String statementText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("while ").append(conditionText).append(" do (").append(statementText).append(")");
    }

    @Override
    public void processAssignmentRule(WhileAssignmentRule assignmentRule) {
        currentBuilder.setLength(0);
        assignmentRule.getAssignment().accept(this);
        String assignmentText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[ass ns]").append("\n").append(assignmentText);
    }

    @Override
    public void processCompRule(WhileCompRule compRule) {
        currentBuilder.setLength(0);
        compRule.getComp().accept(this);
        String compText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[comp ns]").append("\n").append(compText);
    }

    @Override
    public void processIfFFRule(WhileIfFFRule ifFFRule) {
        currentBuilder.setLength(0);
        ifFFRule.getIfStatement().accept(this);
        String ifText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[if ff ns]").append("\n").append(ifText);
    }

    @Override
    public void processIfTTRule(WhileIfTTRule ifTTRule) {
        currentBuilder.setLength(0);
        ifTTRule.getIfStatement().accept(this);
        String ifText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[if tt ns]").append("\n").append(ifText);
    }

    @Override
    public void processSkipRule(WhileSkipRule skipRule) {
        currentBuilder.setLength(0);
        skipRule.getSkip().accept(this);
        String skipText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[skip]").append("\n").append(skipText);
    }

    @Override
    public void processWhileFFRule(WhileWhileFFRule whileFFRule) {
        currentBuilder.setLength(0);
        whileFFRule.getWhileStatement().accept(this);
        String whileText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[while ff ns]").append("\n").append(whileText);
    }

    @Override
    public void processWhileTTRule(WhileWhileTTRule whileTTRule) {
        currentBuilder.setLength(0);
        whileTTRule.getWhileStatement().accept(this);
        String whileText = currentBuilder.toString();

        currentBuilder.setLength(0);
        currentBuilder.append("[while tt ns]").append("\n").append(whileText);
    }

    @Override
    public void processDepthLimit() {
        currentBuilder.setLength(0);
        currentBuilder.append("[...]\n...");
    }

    @Override
    public void processWhileState(WhileState state) {
        currentBuilder.setLength(0);

        if (isExplicitState) {
            currentBuilder.append("s_{");
            for (Map.Entry<WhileVar, Integer> entry : state.getMap().entrySet()) {
                currentBuilder.append(entry.getKey().getVarName()).append("=").append(entry.getValue()).append(", ");
            }
            currentBuilder.setLength(currentBuilder.length() - 2);

            if (state.getMap().entrySet().size() > 0) {
                currentBuilder.append("}");
            }
        } else {
            currentBuilder.append("s_{");
            ArrayList<Pair<WhileVar, Integer>> entrySet = new ArrayList<>();
            for (Map.Entry<WhileVar, Integer> elem : state.getMap().entrySet()) {
                entrySet.add(Pair.of(elem.getKey(), elem.getValue()));
            }
            entrySet.sort(Comparator.comparing(Pair::getFirst));
            for (Pair<WhileVar, Integer> entry : entrySet) {
                currentBuilder.append(entry.getSecond()).append(", ");
            }
            currentBuilder.setLength(currentBuilder.length() - 2);

            if (state.getMap().entrySet().size() > 0) {
                currentBuilder.append("}");
            }
        }
    }
}
