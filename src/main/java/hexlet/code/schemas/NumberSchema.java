package hexlet.code.schemas;

import java.time.temporal.ValueRange;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addValidator(data -> data instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addValidator(data -> data == null || data instanceof Integer && (int) data > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidator(data -> data == null
                || data instanceof Integer
                && ValueRange.of(min, max).isValidIntValue((int) data)
        );
        return this;
    }
}
