package com.epam.jmp.task1;

import java.util.HashMap;
import java.util.Map;

public class CustomCopyOnWriteMap {

    private volatile Map<Integer, Integer> map = new HashMap<>();

    public void put(Integer key, Integer value) {
        synchronized (this) {
            Map<Integer, Integer> copy = new HashMap<>(map);
            copy.put(key, value);
            map = copy;
        }
    }

    public int sumValues() {
        int sum = 0;
        for (int v : map.values()) {
            sum += v;
        }
        return sum;
    }
}

