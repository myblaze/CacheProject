package com.blaze.cache;

/**
 * @author Kerimov_TR
 */
public class CacheValue<V> {

    private final V value;
    private Integer counter;

    public CacheValue(V value) {
        this.value = value;
        this.counter = 0;
    }

    public V getValue() {
        return value;
    }

    public Integer getCounter() {
        return counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public void incCounter() {
        this.counter++;
    }

}
