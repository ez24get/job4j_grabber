package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Parking;

public class ParkingControlPanel {

    private Parking parking;
    private ParkingLogic action;

    public ParkingControlPanel(Parking parking, ParkingLogic action) {
        this.parking = parking;
        this.action = action;
    }

    public boolean parkTo(Car car) {
        boolean isParked = action.acceptCarToParking(car, parking);
        if (isParked) {
            parking.addCar(car);
            parking.setParkingCapacity(parking.getParkingCapacity() - car.getCarSize());
        }
        return isParked;
    }

    public boolean leave(Car car) {
        boolean removeCarIfExists = parking.carExists(car);
        if (removeCarIfExists) {
            parking.removeCar(car);
            parking.setParkingCapacity(parking.getParkingCapacity() + car.getCarSize());
        }
        return removeCarIfExists;
    }

    public double spaceLeft() {
        return parking.getParkingCapacity();
    }

    public int numberOfCars() {
        return parking.getNumberOfCars();
    }
}
