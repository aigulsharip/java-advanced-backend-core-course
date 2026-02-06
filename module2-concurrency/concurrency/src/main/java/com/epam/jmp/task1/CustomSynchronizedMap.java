package com.epam.jmp.task1;

import java.util.HashMap;
import java.util.Map;

public class CustomSynchronizedMap {

    private final Map<Integer, Integer> map = new HashMap<>();

    public synchronized void put(Integer key, Integer value) {
        map.put(key, value);
    }

    public synchronized int sumValues() {
        int sum = 0;
        for (int v : map.values()) {
            sum += v;
        }
        return sum;
    }
}
