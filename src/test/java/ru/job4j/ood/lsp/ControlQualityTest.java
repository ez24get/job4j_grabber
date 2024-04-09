package ru.job4j.ood.lsp;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.modelfood.Food;
import ru.job4j.ood.lsp.storage.Shop;
import ru.job4j.ood.lsp.storage.Store;
import ru.job4j.ood.lsp.storage.Trash;
import ru.job4j.ood.lsp.storage.Warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenAddOk() {
        List<Store> storeList = new ArrayList<>();
        Calendar created = new GregorianCalendar(2024, 3, 12);
        Calendar expired = new GregorianCalendar(2024, 11, 12);
        storeList.add(new Warehouse());
        storeList.add(new Shop());
        storeList.add(new Trash());
        List<Food> foodList = new ArrayList<>();
        Food food = new Food("apple", created, expired, 100, 0);
        foodList.add(food);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.distribute(foodList);
        Food result = storeList.get(2).getList().get(0);
        assertThat(result).isEqualTo(food);
    }
}