package com.example.demo.mapper;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final AtomicLong idGenerator = new AtomicLong(1);

    public Product toEntity(ProductRequestDTO requestDTO) {
        return new Product(
                idGenerator.getAndIncrement(),
                requestDTO.getName(),
                requestDTO.getPrice()
        );
    }

    public Product toEntity(Long id, ProductRequestDTO requestDTO) {
        return new Product(
                id,
                requestDTO.getName(),
                requestDTO.getPrice()
        );
    }

    public ProductResponseDTO toDto(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public List<ProductResponseDTO> toDtoList(List<Product> products) {
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
