package com.example.shop2.es;
import java.time.Instant;
import java.util.UUID;

public abstract class Event {
    private final String eventId = UUID.randomUUID().toString();
    private final Instant occurredAt = Instant.now();

    public String getEventId() {
        return eventId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public abstract String aggregateId();
}
