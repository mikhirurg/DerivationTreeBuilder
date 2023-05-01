package states;

import converter.DerivationTreeConverter;
import converter.WhileDerivationTreeConverter;
import parser.syntax.whilelang.arithmeticexp.WhileVar;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WhileState implements State {
    private final Map<WhileVar, Integer> map;

    public WhileState(Map<WhileVar, Integer> map) {
        this.map = new HashMap<>();
        this.map.putAll(map);
    }

    public WhileState cloneState() {
        return new WhileState(map);
    }

    public void update(WhileVar var, Integer value) {
        map.put(var, value);
    }

    public boolean contains(WhileVar var) {
        return map.containsKey(var);
    }

    public int getIntVal(WhileVar variable) {
        return map.get(variable);
    }

    public Map<WhileVar, Integer> getMap() {
        return map;
    }

    @Override
    public String getTextRepresentation() {
        return map.entrySet().stream()
                .map(e -> e.getKey().textRepresentation() + " = " + e.getValue().toString())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void accept(DerivationTreeConverter converter) {
        ((WhileDerivationTreeConverter) converter).processWhileState(this);
    }
}
