package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.store.MemoryStore;

import java.io.*;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportForProgrammersTest {
    @Test
    public void whenOldGenerated() throws IOException {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        ru.job4j.ood.srp.model.Employee worker = new ru.job4j.ood.srp.model.Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        ReportForProgrammers engine = new ReportForProgrammers(store, parser);
        File file = File.createTempFile("report", "csv");
        String path = file.getPath();
        engine.csvOut(engine.generate(employee -> true), path);
        StringBuilder expected = new StringBuilder()
                .append("Name, Hired, Fired, Salary,")
                .append(System.lineSeparator())
                .append(worker.getName()).append(", ")
                .append(parser.parse(worker.getHired())).append(", ")
                .append(parser.parse(worker.getFired())).append(", ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = builder.toString();
        assertThat(result).isEqualTo(expected.toString());
    }
}