package ru.job4j.ood.lsp.parking.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Parking {
    private Map<String, Double> parkingList = new HashMap<>();
    private double parkingCapacity;

    public Parking(double parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    public void addCar(Car car) {
        parkingList.put(car.getLicencePlate(), car.getCarSize());
    }

    public void removeCar(Car car) {
        if (carExists(car)) {
            parkingList.remove(car.getLicencePlate());
        }
    }

    public boolean carExists(Car car) {
        return parkingList.containsKey(car.getLicencePlate());
    }

    public double getParkingCapacity() {
        return parkingCapacity;
    }

    public void setParkingCapacity(double parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    public int getNumberOfCars() {
        return parkingList.size();
    }

    @Override
    public String toString() {
        return "Parking{"
                + "parkingList=" + parkingList
                + ", parkingCapacity=" + parkingCapacity
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Parking parking = (Parking) o;
        return parkingCapacity == parking.parkingCapacity && Objects.equals(parkingList, parking.parkingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingList, parkingCapacity);
    }
}
