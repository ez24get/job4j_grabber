package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адресс каталога для кэширования");
        String dir = scanner.nextLine();
        AbstractCache<String, String> dirFileCache = new DirFileCache(dir);
        System.out.println("Введите имя файла или exit для выхода");
        String file = scanner.nextLine();
        while (!"exit".equals(file)) {
            System.out.println(dirFileCache.get(file));
            file = scanner.nextLine();
        }
    }
}
