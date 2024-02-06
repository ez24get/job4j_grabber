package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    @Test
    public void parse() {
        String str = "2024-02-05T11:28:38+03:00";
        String result = "2024-02-05T11:28:38";
        HabrCareerDateTimeParser dateTimeParser = new HabrCareerDateTimeParser();
        LocalDateTime localDateTime = dateTimeParser.parse(str);
        assertThat(localDateTime.toString()).isEqualTo(result);
    }
}