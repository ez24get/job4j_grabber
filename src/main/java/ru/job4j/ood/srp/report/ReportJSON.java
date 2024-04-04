package ru.job4j.ood.srp.report;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        return toJSON(list);
    }

    public String toJSON(List<Employee> list) {
        JSONArray jsonArray = new JSONArray(list);
        JSONObject jsonObject;
        for (Employee e : list) {
            jsonObject = new JSONObject();
            jsonObject.put("name", e.getName());
            jsonObject.put("hired", dateTimeParser.parse(e.getHired()));
            jsonObject.put("fired", dateTimeParser.parse(e.getFired()));
            jsonObject.put("salary", e.getSalary());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
