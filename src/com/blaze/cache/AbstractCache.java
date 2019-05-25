package com.blaze.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kerimov_TR
 */
public abstract class AbstractCache<K, V> {

    private final int maxCapacity;
    private final Map<K, CacheValue<V>> map;

    public AbstractCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        map = new HashMap<>(maxCapacity);
    }

    public Map<K, CacheValue<V>> getMap() {
        return map;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int size() {
        return map.size();
    }

    public void clear() {
        map.clear();
    }

    public abstract V get(K key);

    public void add(K key, V value, Comparator comp) {
        if ((getMap().size() == getMaxCapacity()) && !getMap().containsKey(key)) {
            Map.Entry<K, CacheValue<V>> tmp = getMap().entrySet().stream().findFirst().get();
            for (Map.Entry<K, CacheValue<V>> p : getMap().entrySet()) {
                if (comp.compare(p.getValue().getCounter(), tmp.getValue().getCounter()) > 0) {
                    tmp = p;
                }
            }
            getMap().remove(tmp.getKey());
        }
        getMap().put(key, new CacheValue(value));
    }

}
