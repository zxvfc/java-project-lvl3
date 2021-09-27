package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        addValidator(data -> data instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidator(data -> data instanceof Map && ((Map<?, ?>) data).size() == size);
        return this;
    }

    public MapSchema shape(Map<Object, BaseSchema> schemas) {

        addValidator(data -> data instanceof Map && schemas.entrySet().stream()
                .allMatch(sc -> {
                    final Object key = sc.getKey();
                    final BaseSchema validator = sc.getValue();
                    return validator.isValid(((Map<?, ?>) data).get(key));
                }));
        return this;
    }
}
