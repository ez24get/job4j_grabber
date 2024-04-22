package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Car;

import java.util.Map;

public class ParkingTwoCarTypes implements ParkingLogic {

    @Override
    public boolean parkTo(Car car) {
        return false;
    }

    @Override
    public boolean leaveParking(Car car) {
        return false;
    }

    @Override
    public int getCarSize(String licencePlate) {
        return 0;
    }

    @Override
    public String getLicencePlate(Car car) {
        return null;
    }

    @Override
    public int getNumberOfCars() {
        return 0;
    }
}
