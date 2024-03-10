package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Emulator {
    public static final int SET_DIRECTORY = 1;
    public static final int ADD_FILE_TO_CACHE = 2;
    public static final int GET_FILE_FROM_CACHE = 3;

    private String directoryPath;
    private String filePath;
    private String cacheText;
    private DirFileCache dirFileCache;

    public static final String SELECT = "Выберите меню";
    public static final String NAME = "Введите название файла";
    public static final String FILE_PATH = "Введите file path";
    public static final String ADDED_TO_CACHE = "Файл добавлен";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1 чтобы указать директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, чтобы получить содержимое файла и кэша.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) throws IOException {
        Emulator menu = new Emulator();
        Scanner scanner = new Scanner(System.in);
        menu.start(scanner);
    }

    private void start(Scanner scanner) throws IOException {
        boolean run = true;
        boolean directoryWasSet = false;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (SET_DIRECTORY == userChoice) {
                directoryWasSet = false;
                while (!directoryWasSet) {
                    System.out.println(FILE_PATH);
                    directoryPath = scanner.nextLine();
                    Path path1 = Path.of(directoryPath);
                    if (path1.toFile().isDirectory()) {
                        dirFileCache = new DirFileCache(directoryPath);
                        directoryWasSet = true;
                    }
                }
            } else if (ADD_FILE_TO_CACHE == userChoice && directoryWasSet) {
                boolean fileExists = false;
                while (!fileExists) {
                    System.out.println("Enter file name");
                    String fileName = scanner.nextLine();
                    File file = Path.of(directoryPath + "/" + fileName + ".txt").toFile();
                    if (file.exists()) {
                        fileExists = true;
                        dirFileCache.put(fileName, getFileContent(file.getPath()));
                        System.out.println(ADDED_TO_CACHE);
                    } else if (!file.exists()) {
                        System.out.println("Try another name, file doesn't exist");
                    }
                }
            } else if (GET_FILE_FROM_CACHE == userChoice && directoryWasSet) {
                boolean fileExists = false;
                while (!fileExists) {
                    System.out.println(NAME);
                    String name = scanner.nextLine();
                    if (dirFileCache.loadContent(name).isEmpty()) {
                        System.out.println("Try another file name");
                    } else if (dirFileCache.loadContent(name).isEmpty()) {
                        fileExists = true;
                        System.out.println(dirFileCache.loadContent(name));
                    }
                }
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public String getFileContent(String fileName) throws IOException {
        String content;
        try {
            content = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
