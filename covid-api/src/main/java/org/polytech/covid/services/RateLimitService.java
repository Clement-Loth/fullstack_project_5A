package org.polytech.covid.services;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Service
public class RateLimitService {
    private final Map<Long, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(long id) {
        return cache.computeIfAbsent(id, this::newBucket);
    }

    public Bucket newBucket(long id) {
        int capacity = id < 0 ? 200 : 30;
        return Bucket.builder()
                     .addLimit(Bandwidth.classic(capacity, Refill.intervally(capacity, Duration.ofMinutes(1))))
                     .build();
    }
}
