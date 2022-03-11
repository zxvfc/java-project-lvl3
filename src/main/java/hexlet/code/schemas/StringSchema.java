package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        required(String.class);
        addValidator(data -> !((String) data).isBlank());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addValidator(data -> ((String) data).length() >= minLength);
        return this;
    }

    public StringSchema contains(String search) {
        addValidator(data -> ((String) data).contains(search));
        return this;
    }
}
