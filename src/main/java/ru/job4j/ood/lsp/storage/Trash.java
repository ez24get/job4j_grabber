package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.datecheck.ExpiryDateCheck;
import ru.job4j.ood.lsp.modelfood.Food;

public class Trash extends AbstractStore {
    private final double percentLowerEnd = 0;
    private final double percentUpperEnd = 25;
    private ExpiryDateCheck dateCheck = new ExpiryDateCheck();

    @Override
    public boolean accept(Food food) {
        return dateCheck.check(food, percentLowerEnd, percentUpperEnd);
    }
}
