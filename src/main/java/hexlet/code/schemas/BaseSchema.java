package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate<Object>> selectedValidators = new ArrayList<>();

    private boolean required = false;

    public abstract BaseSchema required();

    protected final void required(final Class<?> expected) {
        required = true;
        selectedValidators.add(0, expected::isInstance);
    }

    protected final void addValidator(Predicate<Object> validator) {
        selectedValidators.add(validator);
    }

    public final boolean isValid(Object data) {
        if (!required && data == null) {
            return true;
        }
        return data != null && this.selectedValidators.stream().allMatch(validator -> validator.test(data));
    }
}
