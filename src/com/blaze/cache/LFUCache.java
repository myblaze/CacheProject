package com.blaze.cache;

import java.util.Comparator;

/**
 * @author Kerimov_TR
 */
public class LFUCache<K, V> extends AbstractCache<K, V> {

    public LFUCache(int maxCapacity) {
        super(maxCapacity);
    }

    @Override
    public V get(K key) {
        CacheValue<V> cacheValue = getMap().get(key);
        if (cacheValue == null) {
            return null;
        }
        V value = cacheValue.getValue();
        if (value != null) {
            getMap().get(key).incCounter();
        }
        return value;
    }

    public void add(K key, V value) {
        super.add(key, value, Comparator.reverseOrder());
    }

}
