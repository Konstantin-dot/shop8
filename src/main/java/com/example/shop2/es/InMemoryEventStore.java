package com.example.shop2.es;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryEventStore {
    private final Map<String, List<Event>> store = new ConcurrentHashMap<>();

    public synchronized void append(String aggregateId, Event e) {
        store.computeIfAbsent(aggregateId, id -> new ArrayList<>()).add(e);
        System.out.println("Added event for " + aggregateId + " to cart");
    }

    public List<Event> load(String aggregateId) {
        return Collections.unmodifiableList(store.getOrDefault(aggregateId, List.of()));
    }

    public Map<String, List<Event>> allEvents() {
        return Collections.unmodifiableMap(store);
    }
}
