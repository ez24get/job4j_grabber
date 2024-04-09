package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.modelfood.Food;

import java.util.List;

public interface Store {
    boolean add(Food food);

    List<Food> getList();
}
