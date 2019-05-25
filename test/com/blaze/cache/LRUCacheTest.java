package com.blaze.cache;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kerimov_TR
 */
public class LRUCacheTest {

    private static LRUCache<Object, Object> instance;

    public LRUCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        instance = new LRUCache(3);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance.clear();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCounter() {
        System.out.println("testCounter");
        instance.add(1, "aaa");
        instance.add(2, "bbb");
        instance.add(3, "ccc");
        instance.get(1);
        int[] objs = instance.getMap().values().stream().mapToInt((m) -> m.getCounter()).toArray();
        int[] expResult = new int[]{0, 2, 1};
        assertArrayEquals(expResult, objs);
    }

    @Test
    public void testCacheValues() {
        System.out.println("testCacheValues");
        instance.add(1, "aaa");
        instance.add(2, "bbb");
        instance.add(3, "ccc");
        instance.get(1);
        instance.add(4, "ddd");
        instance.add(5, "eee");
        Object[] objs = instance.getMap().values().stream().map((m) -> m.getValue()).toArray();
        Object[] expResult = new Object[]{"ddd", "aaa", "eee"};
        assertArrayEquals(expResult, objs);
    }

    /**
     * Test of size method, of class LRUCache.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        instance.add(1, "aaa");
        instance.add(2, "bbb");
        int expResult = 2;
        int result = instance.size();
        assertEquals(expResult, result);
        instance.add(3, "ccc");
        instance.add(3, "ddd");
        expResult = 3;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class LRUCache.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Object result = instance.get(null);
        assertEquals(null, result);
    }

    /**
     * Test of add method, of class LRUCache.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Object key = null;
        Object value = null;
        instance.add(key, value);
        Object expResult = null;
        Object result = instance.get(key);
        assertEquals(expResult, result);
    }

}
