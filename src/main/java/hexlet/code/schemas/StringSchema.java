package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addValidator(data -> data instanceof String && !((String) data).isBlank());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addValidator(data -> data instanceof String && ((String) data).length() >= minLength);
        return this;
    }

    public StringSchema contains(String search) {
        addValidator(data -> data instanceof String && ((String) data).contains(search));
        return this;
    }
}
