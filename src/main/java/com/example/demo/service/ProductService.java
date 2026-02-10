package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product product) {
        product.setId(id);
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}
