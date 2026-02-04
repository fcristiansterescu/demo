package com.example.demo.aop;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class MethodCallCounter {

    private final ConcurrentHashMap<String, AtomicLong> counter = new ConcurrentHashMap<String, AtomicLong>();

    public long increment(String methodName) {
        return counter
                .computeIfAbsent(methodName, k -> new AtomicLong(0))
                .incrementAndGet();
    }
}
