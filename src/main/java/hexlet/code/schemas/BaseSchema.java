package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> selectedValidators = new ArrayList<>();

    protected final void addValidator(Predicate<Object> validator) {
        selectedValidators.add(validator);
    }

    public final boolean isValid(Object data) {
        return this.selectedValidators.stream().allMatch(validator -> validator.test(data));
    }
}
