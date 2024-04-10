package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.datecheck.ExpiryDateCheck;
import ru.job4j.ood.lsp.modelfood.Food;

public class Shop extends AbstractStore {
    private final double percentLowerEnd = 25;
    private final double percentUpperEnd = 75;
    private ExpiryDateCheck dateCheck = new ExpiryDateCheck();

    @Override
    public boolean accept(Food food) {
        return dateCheck.check(food, percentLowerEnd, percentUpperEnd);
    }
}
