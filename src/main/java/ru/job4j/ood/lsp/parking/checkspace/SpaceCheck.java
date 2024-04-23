package ru.job4j.ood.lsp.parking.checkspace;

public class SpaceCheck {
    public boolean checkSpace(double carSize, double spaceLeft) {
        return spaceLeft - carSize >= 0;
    }
}
