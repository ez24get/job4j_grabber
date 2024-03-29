package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.store.Store;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportForProgrammers implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportForProgrammers(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<ru.job4j.ood.srp.model.Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name, Hired, Fired, Salary,")
                .append(System.lineSeparator());
        for (ru.job4j.ood.srp.model.Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(", ")
                    .append(dateTimeParser.parse(employee.getHired())).append(", ")
                    .append(dateTimeParser.parse(employee.getFired())).append(", ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public void csvOut(String report, String fileName) {
        try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fileName))) {
            output.write(report.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
