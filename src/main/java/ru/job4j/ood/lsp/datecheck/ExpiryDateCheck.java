package ru.job4j.ood.lsp.datecheck;

import ru.job4j.ood.lsp.modelfood.Food;
import java.util.Calendar;

public class ExpiryDateCheck {
    public boolean check(Food food, double lowEnd, double upEnd) {
        Calendar start = food.getCreateDate();
        Calendar end = food.getExpiryDate();
        Calendar now = Calendar.getInstance();
        double daysTotal = end.getTimeInMillis() - start.getTimeInMillis();
        double daysPassed = now.getTimeInMillis() - start.getTimeInMillis();
        double percentage = 100 - (daysPassed / daysTotal) * 100;
        return lowEnd <= percentage && upEnd >= percentage;
    }
}
