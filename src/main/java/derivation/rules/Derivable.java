package derivation.rules;

import converter.DerivationTreeConverter;
import states.State;

public interface Derivable {
    State getState();

    void accept(DerivationTreeConverter converter);
}
