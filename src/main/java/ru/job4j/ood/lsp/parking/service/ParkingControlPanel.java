package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Car;

import java.util.Map;

public class ParkingControlPanel {
    private Map<String, Integer> car;

    public ParkingControlPanel(Map<String, Integer> car) {
        this.car = car;
    }

    public void parkTo(Map<String, Integer> car) {
    }

    public void leave(Map<String, Integer> car) {
    }
}
