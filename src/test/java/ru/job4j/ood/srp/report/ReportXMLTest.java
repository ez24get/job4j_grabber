package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportXMLTest {
    @Test
    public void whenOldGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("In", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker1);
        Report engine = new ReportXML(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append("        <name>").append(worker.getName()).append("</name>\n")
                .append("        <hired>").append(parser.parse(worker.getHired())).append("</hired>\n")
                .append("        <fired>").append(parser.parse(worker.getFired())).append("</fired>\n")
                .append("        <salary>").append(worker.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("    <employee>\n")
                .append("        <name>").append(worker1.getName()).append("</name>\n")
                .append("        <hired>").append(parser.parse(worker1.getHired())).append("</hired>\n")
                .append("        <fired>").append(parser.parse(worker1.getFired())).append("</fired>\n")
                .append("        <salary>").append(worker1.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("</employees>\n");
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}