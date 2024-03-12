package ru.job4j.cache;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try {
            String content = Files.readString(Paths.get(key));
            put(key, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return get(key);
    }
}