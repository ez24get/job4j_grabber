package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.modelfood.Food;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractStore implements Store {
    private List<Food> foodList = new ArrayList<>();

    public boolean add(Food food) {
        return foodList.add(food);
    }

    @Override
    public List<Food> getList() {
        return foodList;
    }
}
