package ru.job4j.ood.lsp.parking.service;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Parking;

public interface ParkingLogic {
    boolean acceptCarToParking(Car car, Parking parking);
}
