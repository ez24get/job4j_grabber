package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.datecheck.ExpiryDateCheck;
import ru.job4j.ood.lsp.modelfood.Food;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractStore implements Store {
    private List<Food> foodList = new ArrayList<>();

    public void add(Food food) {
        foodList.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return true;
    }

    @Override
    public List<Food> getList() {
        return foodList;
    }
}
