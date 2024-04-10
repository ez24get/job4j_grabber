package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.modelfood.Food;

import java.util.List;

public interface Store {
    void add(Food food);

    boolean accept(Food food);

    List<Food> getList();
}
