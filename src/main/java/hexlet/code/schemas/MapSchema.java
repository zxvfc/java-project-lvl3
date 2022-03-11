package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        required(Map.class);
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidator(data -> ((Map<?, ?>) data).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {

        addValidator(data -> schemas.entrySet().stream()
                .allMatch(sc -> {
                    final Object key = sc.getKey();
                    final BaseSchema validator = sc.getValue();
                    return validator.isValid(((Map<?, ?>) data).get(key));
                }));
        return this;
    }
}
