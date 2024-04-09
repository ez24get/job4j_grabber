package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.modelfood.Food;
import ru.job4j.ood.lsp.datecheck.ExpiryDateCheck;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractStore implements Store {
    private List<Food> foodList = new ArrayList<>();

    public void add(Food food) {
        foodList.add(food);
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}
