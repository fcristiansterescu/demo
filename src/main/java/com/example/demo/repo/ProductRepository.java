package com.example.demo.repo;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Product save(Product product) {
        long id = idGenerator.incrementAndGet();
        Product saved = new Product(id, product.getName(), product.getPrice());
        store.put(id, saved);
        return saved;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Product> findAll() {
        return store.values()
                .stream()
                .sorted(Comparator.comparing(Product::getId))
                .collect(Collectors.toList());
    }

    public Product update(Long id, Product product) {
        Product updated = new Product(id, product.getName(), product.getPrice());
        store.put(id, updated);
        return updated;
    }

    public void delete(Long id) {
        store.remove(id);
    }

}
