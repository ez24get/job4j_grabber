package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.modelfood.Food;
import ru.job4j.ood.lsp.datecheck.ExpiryDateCheck;

public class Warehouse extends AbstractStore {
    private final double percentLowerEnd = 75;
    private final double percentUpperEnd = 100;
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
