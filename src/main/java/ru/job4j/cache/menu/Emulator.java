package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Random;
import java.util.Scanner;

public class Emulator {
    public static final int SET_DIRECTORY = 1;
    public static final int ADD_FILE_TO_CACHE = 2;
    public static final int GET_FILE_FROM_CACHE = 3;
    private String directoryPath;
    private String cacheText;
    private AbstractCache<String, String> abstractCache;

    public static final String SELECT = "Выберите меню";
    public static final String NAME = "Введите название файла";
    public static final String TEXT = "Введите текст файла";
    public static final String FILE_PATH = "Введите file path";
    public static final String ADDED_TO_CACHE = "Файл добавлен";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1 чтобы указать директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, чтобы получить содержимое файла и кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Emulator menu = new Emulator();
        Scanner scanner = new Scanner(System.in);
        menu.start(scanner);
    }

    private void start(Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (SET_DIRECTORY == userChoice) {
                System.out.println(FILE_PATH);
                String path = scanner.nextLine();

            } else if (ADD_FILE_TO_CACHE == userChoice) {
                String path = scanner.nextLine();
                System.out.println(ADDED_TO_CACHE);
            } else if (GET_FILE_FROM_CACHE == userChoice) {
                System.out.println(FILE_PATH);
                String path = scanner.nextLine();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
