package ap.lox;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LoxInstance {
    private LoxClass klcass;
    private final Map<String, Object> fields = new HashMap<String, Object>();

    LoxInstance(LoxClass klcass) {
        this.klcass = klcass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        LoxFunction method = klcass.findMethod(this, name.lexeme);
        if (method != null) return method;

        throw new RuntimeError(name, "Undefined property '" + name.lexeme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString() {
        return klcass.name + " instance";
    }
}
