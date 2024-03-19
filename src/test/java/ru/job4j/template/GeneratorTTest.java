package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class GeneratorTTest {
    public void whenTestOk() throws IOException {
        Generator generator = new GeneratorT();
        String template = "Hello ${name}, bye ${surname}";
        Map<String, String> args = new HashMap<>();
        args.put("name", "john");
        args.put("surname", "G");
        assertThat(generator.produce(template, args)).isEqualTo("Hello john, bye G");
    }

    public void whenTestKeyIsMissing() throws IOException {
        Generator generator = new GeneratorT();
        String template = "Hello ${name}, bye ${surname}";
        Map<String, String> args = new HashMap<>();
        args.put("name", "john");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IOException.class);
    }

    public void whenTestKeyIsWrong() throws IOException {
        Generator generator = new GeneratorT();
        String template = "Hello ${name}, bye ${surname}";
        Map<String, String> args = new HashMap<>();
        args.put("name", "john");
        args.put("fame", "john");
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IOException.class);
    }
}