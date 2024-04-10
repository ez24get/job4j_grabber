package ru.job4j.ood.lsp.parking.model;

import java.util.Objects;

public class Car {
    private String licencePlate;
    private int carSize;

    public Car(String licencePlate, int carSize) {
        this.licencePlate = licencePlate;
        this.carSize = carSize;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getCarSize() {
        return carSize;
    }

    public void setCarSize(int carSize) {
        this.carSize = carSize;
    }

    @Override
    public String toString() {
        return "Car{"
                + "licencePlate='" + licencePlate + '\''
                + ", carSize=" + carSize
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
        Car car = (Car) o;
        return carSize == car.carSize && Objects.equals(licencePlate, car.licencePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licencePlate, carSize);
    }
}
