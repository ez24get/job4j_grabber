package ru.job4j.ood.lsp.parking.service;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.ControlQuality;
import ru.job4j.ood.lsp.modelfood.Food;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Parking;
import ru.job4j.ood.lsp.storage.Shop;
import ru.job4j.ood.lsp.storage.Store;
import ru.job4j.ood.lsp.storage.Trash;
import ru.job4j.ood.lsp.storage.Warehouse;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ParkingControlPanelTest {
    @Test
    public void whenAddOk() {
        Car car1 = new Car("777", 1);
        ParkingLogic logic = new ParkingTwoCarTypes();
        ParkingControlPanel parkingControlPanel = new ParkingControlPanel(new Parking(100), logic);
        boolean parked = parkingControlPanel.parkTo(car1);
        assertThat(parked).isTrue();
    }

    @Test
    public void whenLeaveOk() {
        Car car1 = new Car("777", 1);
        ParkingLogic logic = new ParkingTwoCarTypes();
        ParkingControlPanel parkingControlPanel = new ParkingControlPanel(new Parking(100), logic);
        boolean parked = parkingControlPanel.leave(car1);
        assertThat(parked).isTrue();
    }

    @Test
    public void whenAddThenGetNumberOfCarsIsOne() {
        Car car1 = new Car("777", 1);
        ParkingLogic logic = new ParkingTwoCarTypes();
        ParkingControlPanel parkingControlPanel = new ParkingControlPanel(new Parking(100), logic);
        parkingControlPanel.parkTo(car1);
        int parked = parkingControlPanel.numberOfCars();
        assertThat(parked).isEqualTo(1);
    }

    @Test
    public void whenAddThreeThenLeaveOneIsOneLeft() {
        Car car1 = new Car("777", 1);
        Car car2 = new Car("888", 1.5);
        Car car3 = new Car("999", 1.5);
        ParkingLogic logic = new ParkingTwoCarTypes();
        ParkingControlPanel parkingControlPanel = new ParkingControlPanel(new Parking(100), logic);
        parkingControlPanel.parkTo(car1);
        parkingControlPanel.parkTo(car2);
        parkingControlPanel.parkTo(car3);
        parkingControlPanel.leave(car1);
        int parked = parkingControlPanel.numberOfCars();
        assertThat(parked).isEqualTo(2);
    }

    @Test
    public void whenCapacity100AddThreeThenSpaceLeftIs96() {
        Car car1 = new Car("777", 1);
        Car car2 = new Car("888", 1.5);
        Car car3 = new Car("999", 1.5);
        ParkingLogic logic = new ParkingTwoCarTypes();
        ParkingControlPanel parkingControlPanel = new ParkingControlPanel(new Parking(100), logic);
        parkingControlPanel.parkTo(car1);
        parkingControlPanel.parkTo(car2);
        parkingControlPanel.parkTo(car3);
        double spaceLeft = parkingControlPanel.spaceLeft();
        assertThat(spaceLeft).isEqualTo(96);
    }

    @Test
    public void whenNoSpaceLeft() {
        Car car1 = new Car("777", 1);
        Car car2 = new Car("888", 1.5);
        Car car3 = new Car("999", 1.5);
        ParkingLogic logic = new ParkingTwoCarTypes();
        ParkingControlPanel parkingControlPanel = new ParkingControlPanel(new Parking(3), logic);
        parkingControlPanel.parkTo(car1);
        parkingControlPanel.parkTo(car2);
        boolean parked = parkingControlPanel.parkTo(car3);
        assertThat(parked).isFalse();
    }

    @Test
    public void whenTheresNoSuchCarToLeave() {
        Car car1 = new Car("777", 1);
        Car car2 = new Car("888", 1.5);
        Car car3 = new Car("999", 1.5);
        ParkingLogic logic = new ParkingTwoCarTypes();
        ParkingControlPanel parkingControlPanel = new ParkingControlPanel(new Parking(100), logic);
        parkingControlPanel.parkTo(car1);
        parkingControlPanel.parkTo(car2);
        boolean parked = parkingControlPanel.leave(car3);
        assertThat(parked).isFalse();
    }
}