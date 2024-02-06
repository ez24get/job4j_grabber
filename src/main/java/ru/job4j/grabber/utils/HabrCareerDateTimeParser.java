package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class HabrCareerDateTimeParser implements DateTimeParser {

    private final DateTimeFormatter formatter = ISO_DATE_TIME;

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(parse, formatter);
    }

    public static void main(String[] args) {
        HabrCareerDateTimeParser o = new HabrCareerDateTimeParser();
        System.out.println(o.parse("2024-02-05T11:28:38+03:00"));
    }
}