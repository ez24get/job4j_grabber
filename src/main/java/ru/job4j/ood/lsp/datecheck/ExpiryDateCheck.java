package ru.job4j.ood.lsp.datecheck;

import ru.job4j.ood.lsp.modelfood.Food;
import java.util.Calendar;

public class ExpiryDateCheck {
    public boolean check(Food food, double lowEnd, double upEnd) {
        Calendar start = food.getCreateDate();
        Calendar end = food.getExpiryDate();
        double percentage = (double) (end.getTimeInMillis() - start.getTimeInMillis()) / 100;
        return lowEnd <= percentage && upEnd >= percentage;
    }
}
