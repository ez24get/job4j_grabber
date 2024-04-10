package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Car;

public interface ParkingLogic {
    boolean parkTo(Car car);

    boolean leaveParking(Car car);
}
