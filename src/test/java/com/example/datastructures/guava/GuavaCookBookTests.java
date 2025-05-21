package com.example.datastructures.guava;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GuavaCookBookTests {

    @Test
    public void testGetAndPut() {
        ListMultimap<String, String> listMultimap = LinkedListMultimap.create();
        listMultimap.put("lettreA", "Albert");
        listMultimap.put("lettreA", "Alphonse");

        assertEquals(2, listMultimap.get("lettreA").size());
        assertEquals("Albert", listMultimap.get("lettreA").get(0));
        assertEquals("Alphonse", listMultimap.get("lettreA").get(1));
    }


    @Test
    public void testAsMap() {
        ListMultimap<String, String> listMultimap = LinkedListMultimap.create();
        listMultimap.put("lettreA", "Albert");
        listMultimap.put("lettreA", "Alphonse");

        assertEquals(2, listMultimap.asMap().get("lettreA").size());
        assertEquals("Albert", ((List) listMultimap.asMap().get("lettreA")).get(0));
    }

    @Test
    public void testEntriesAndKeys() {
        ListMultimap<String, String> wierdMap = LinkedListMultimap.create();
        wierdMap.put("lettreA", "Alex");
        wierdMap.put("lettreA", "Ali");
        wierdMap.put("lettreB", "Bob");

        assertEquals(3, wierdMap.entries().size());
        assertEquals(2, wierdMap.keySet().size());
    }


    @Test
    public void testFlatten() {
        ListMultimap<String, String> wierdMap = LinkedListMultimap.create();
        wierdMap.put("lettreA", "Alex");
        wierdMap.put("lettreA", "Ali");
        wierdMap.put("lettreB", "Bob");

        List<Map.Entry> flatList = makeFlat(wierdMap);

        assertEquals(3, flatList.size());
        assertEquals("lettreA", flatList.get(0).getKey());
        assertEquals("Alex", flatList.get(0).getValue());

        assertEquals("lettreA", flatList.get(1).getKey());
        assertEquals("Ali", flatList.get(1).getValue());

        assertEquals("lettreB", flatList.get(2).getKey());
        assertEquals("Bob", flatList.get(2).getValue());
    }

    @Test
    public void testIDEConditionalBreakPoint() {
        List names = new ArrayList();
        names.add("one");
        names.add("two");
        names.add(null);
        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> this.print(names));
        assertEquals("java.lang.NullPointerException", exception.getClass().getName());
    }



    private List<Map.Entry> makeFlat(ListMultimap<String, String> map) {
        return (List) map.entries();
    }

    public void print(List<String> names) {
        for (String name : names) {
            System.out.println(name.toString());
        }
    }


}
