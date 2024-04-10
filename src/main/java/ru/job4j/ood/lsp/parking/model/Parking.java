package ru.job4j.ood.lsp.parking.model;

import java.util.List;
import java.util.Objects;

public class Parking {
    private List<Car> parkingList;
    private int parkingCapacity;

    public Parking(List<Car> parkingList, int parkingCapacity) {
        this.parkingList = parkingList;
        this.parkingCapacity = parkingCapacity;
    }

    public List<Car> getParkingList() {
        return parkingList;
    }

    public void setParkingList(List<Car> parkingList) {
        this.parkingList = parkingList;
    }

    public int getParkingCapacity() {
        return parkingCapacity;
    }

    public void setParkingCapacity(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
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
