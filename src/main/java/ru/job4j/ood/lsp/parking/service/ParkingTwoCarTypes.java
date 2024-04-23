package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.checkspace.SpaceCheck;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Parking;

public class ParkingTwoCarTypes implements ParkingLogic {
    private SpaceCheck spaceCheck = new SpaceCheck();

    @Override
    public boolean acceptCarToParking(Car car, Parking parking) {
        return spaceCheck.checkSpace(car.getCarSize(), parking.getParkingCapacity());
    }
}
