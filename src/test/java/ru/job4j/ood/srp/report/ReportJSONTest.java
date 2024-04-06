package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJSONTest {
    @Test
    public void whenOldGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("In", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker1);
        Report engine = new ReportJSON(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("[")
                .append(System.lineSeparator())
                .append("  {")
                .append(System.lineSeparator())
                .append("    \"name\": ")
                .append("\"").append(worker.getName()).append("\",")
                .append(System.lineSeparator())
                .append("    \"hired\": ")
                .append("\"").append(parser.parse(worker.getHired())).append("\",")
                .append(System.lineSeparator())
                .append("    \"fired\": ")
                .append("\"").append(parser.parse(worker.getFired())).append("\",")
                .append(System.lineSeparator())
                .append("    \"salary\": ")
                .append(worker.getSalary())
                .append(System.lineSeparator())
                .append("  },")
                .append(System.lineSeparator())
                .append("  {")
                .append(System.lineSeparator())
                .append("    \"name\": ")
                .append("\"").append(worker1.getName()).append("\",")
                .append(System.lineSeparator())
                .append("    \"hired\": ")
                .append("\"").append(parser.parse(worker1.getHired())).append("\",")
                .append(System.lineSeparator())
                .append("    \"fired\": ")
                .append("\"").append(parser.parse(worker1.getFired())).append("\",")
                .append(System.lineSeparator())
                .append("    \"salary\": ")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append("  }")
                .append(System.lineSeparator())
                .append("]");
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}