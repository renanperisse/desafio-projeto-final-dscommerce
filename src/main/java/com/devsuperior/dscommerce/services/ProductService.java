package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dtos.ProductDTO;
import com.devsuperior.dscommerce.dtos.ProductMinDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository productRepository) {
        repository = productRepository;
    }

    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> products = repository.findByNameContainingIgnoreCase(name, pageable);
        return products.map(ProductMinDTO::new);
    }

    public ProductDTO findById(Long id) {
        Optional<Product> product = repository.findProductWithCategoriesById(id);
        Product result = (product.get());
        return new ProductDTO(result);
    }
}
