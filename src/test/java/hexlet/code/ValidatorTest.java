package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

class ValidatorTest {

    @Test
    void testStringValidator() {
        final String stringToCheck = "what does the fox say";

        final int minLength = 5;
        final Validator v = new Validator();
        final StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();
        assertTrue(schema.isValid(stringToCheck));
        assertTrue(schema.isValid(stringToCheck));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));

        schema.minLength(minLength);
        assertFalse(schema.isValid("asdf"));
        assertTrue(schema.isValid(stringToCheck));

        schema.contains("what");
        assertTrue(schema.isValid(stringToCheck));

        schema.contains("whatttt");
        assertFalse(schema.isValid(stringToCheck));
    }

    @Test
    void testNumberValidator() {
        final Validator v = new Validator();
        final NumberSchema schema = v.number();

        final int ten = 10;
        final int tenNegative = -10;
        final int five = 5;
        final int four = 4;
        final int eleven = 11;

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid("5"));
        assertTrue(schema.positive().isValid(ten));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(tenNegative));

        schema.range(five, ten);
        assertTrue(schema.isValid(five));
        assertTrue(schema.isValid(ten));
        assertFalse(schema.isValid(four));
        assertFalse(schema.isValid(eleven));
    }

    @Test
    void testMapValidator() {
        final Validator v = new Validator();
        final MapSchema schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        final Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testMapValueValidator() {
        final Validator v = new Validator();
        Map<String, BaseSchema> schemas = Map.of("name", v.string().required(),
                                                 "age", v.number().positive());

        final int hundred = 100;
        final int fiveNegative = -5;

        final MapSchema schema = v.map().shape(schemas);
        Map<String, Object> human1 = Map.of("name", "Kolya",
                                            "age", hundred);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human4 = Map.of("name", "Valya",
                                            "age", fiveNegative);
        assertFalse(schema.isValid(human4));
    }
}
