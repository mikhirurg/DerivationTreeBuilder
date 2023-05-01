package states;

import converter.DerivationTreeConverter;

public interface State {
    public String getTextRepresentation();

    void accept(DerivationTreeConverter converter);
}
