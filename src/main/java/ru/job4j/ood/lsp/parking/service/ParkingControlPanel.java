package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Parking;

import java.util.Map;

public class ParkingControlPanel {

    private Parking parking;

    public ParkingControlPanel(Parking parking, ParkingLogic action) {
        this.parking = parking;
        this.action = action;
    }

    private ParkingLogic action;

    public ParkingControlPanel(Parking parking) {
        this.parking = parking;
    }

    public boolean parkTo(Car car) {
        return false;
    }

    public boolean leave(Car car) {
        return false;
    }

    public double spaceLeft() {
        return parking.getParkingCapacity();
    }

    public int numberOfCars() {
        return parking.getNumberOfCars();
    }
}
