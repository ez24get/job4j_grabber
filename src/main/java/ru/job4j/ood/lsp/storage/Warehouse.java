package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.modelfood.Food;
import ru.job4j.ood.lsp.datecheck.ExpiryDateCheck;

public class Warehouse extends AbstractStore {
    private final double percentLowerEnd = 75;
    private final double percentUpperEnd = 100;
    private ExpiryDateCheck dateCheck = new ExpiryDateCheck();

    @Override
    public void add(Food food) {
        if (dateCheck.check(food, percentLowerEnd, percentUpperEnd)) {
            add(food);
        }
    }
}
