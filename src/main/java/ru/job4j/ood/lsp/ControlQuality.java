package ru.job4j.ood.lsp;

import ru.job4j.ood.lsp.modelfood.Food;
import ru.job4j.ood.lsp.storage.Shop;
import ru.job4j.ood.lsp.storage.Store;
import ru.job4j.ood.lsp.storage.Trash;
import ru.job4j.ood.lsp.storage.Warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlQuality {
    private List<Store> list;

    public ControlQuality(List<Store> list) {
        this.list = list;
    }

    public void distribute(List<Food> foodList) {
        for (Store s : list) {
            for (Food food : foodList) {
                if (s.accept(food)) {
                    s.add(food);
                }
            }
        }
    }

    public void resort() {
        List<Food> foodListResort = new ArrayList<>();
        for (Store s : list) {
            foodListResort.addAll(s.getList());
        }
        for (Store s : list) {
            for (Food food : foodListResort) {
                if (s.accept(food)) {
                    s.add(food);
                }
            }
        }
    }
}
