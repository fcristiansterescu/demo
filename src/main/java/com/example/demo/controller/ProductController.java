package com.example.demo.controller;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return mapper.toDtoList(service.getAll());
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO requestDTO) {

        Product createdProduct = service.create(mapper.toEntity(requestDTO));

        return mapper.toDto(createdProduct);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateById(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) {

        Product updatedProduct = service.update(id, mapper.toEntity(id, requestDTO));

        return mapper.toDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
