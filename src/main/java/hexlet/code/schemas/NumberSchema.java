package hexlet.code.schemas;

import java.time.temporal.ValueRange;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        required(Integer.class);
        return this;
    }

    public NumberSchema positive() {
        addValidator(data -> (int) data > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidator(data -> ValueRange.of(min, max).isValidIntValue((int) data));
        return this;
    }
}
