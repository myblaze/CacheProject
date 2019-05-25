package com.blaze.cache;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Kerimov_TR
 */
public class LRUCache<K, V> extends AbstractCache<K, V> {

    public LRUCache(int maxCapacity) {
        super(maxCapacity);
    }

    private void incrementCounters(K key) {
        getMap().get(key).resetCounter();
        for (Map.Entry<K, CacheValue<V>> t : getMap().entrySet()) {
            if (t.getKey() != null && !t.getKey().equals(key)) {
                getMap().get(t.getKey()).incCounter();
            }
        }
    }

    @Override
    public V get(K key) {
        CacheValue<V> cacheValue = getMap().get(key);
        if (cacheValue == null) {
            return null;
        }
        V value = cacheValue.getValue();
        if (value != null) {
            incrementCounters(key);
        }
        return value;
    }

    public void add(K key, V value) {
        super.add(key, value, Comparator.naturalOrder());
        incrementCounters(key);
    }

}
