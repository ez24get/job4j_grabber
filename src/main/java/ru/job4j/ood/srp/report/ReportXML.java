package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportXML(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        try {
            return toXML(list);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public String toXML(List<Employee> list) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            jaxbMarshaller.marshal(new Employees(list), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}
