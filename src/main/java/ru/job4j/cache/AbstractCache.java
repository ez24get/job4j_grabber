package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> softReference = new SoftReference<>(value);
        cache.put(key, softReference);
    }

    public final V get(K key) {
        if (!cache.containsKey(key)) {
            put(key, load(key));
            System.out.println(get(key));
        }
        SoftReference<V> softReference = cache.get(key);
        return softReference.get();
    }

    protected abstract V load(K key);
}
