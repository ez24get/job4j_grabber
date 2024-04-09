package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.datecheck.ExpiryDateCheck;
import ru.job4j.ood.lsp.modelfood.Food;

public class Shop extends AbstractStore {
    private final double percentLowerEnd = 25;
    private final double percentUpperEnd = 75;
    private ExpiryDateCheck dateCheck = new ExpiryDateCheck();

    @Override
    public boolean add(Food food) {
        boolean addToList = dateCheck.check(food, percentLowerEnd, percentUpperEnd);
        if (dateCheck.check(food, percentLowerEnd, percentUpperEnd)) {
            add(food);
        }
        return addToList;
    }
}
