package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dtos.CategoryDTO;
import com.devsuperior.dscommerce.dtos.ProductDTO;
import com.devsuperior.dscommerce.dtos.ProductMinDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.CategoryRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;
    private CategoryRepository catRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        repository = productRepository;
        catRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> products = repository.findByNameContainingIgnoreCase(name, pageable);
        return products.map(ProductMinDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> product = repository.findProductWithCategoriesById(id);
        Product result = (product.get());
        return new ProductDTO(result);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        copyDtoForEntity(product, dto);
        repository.save(product);
        return new ProductDTO(product);
    }

    private void copyDtoForEntity(Product product, ProductDTO dto) {
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());

        product.getCategories().clear();
        for (CategoryDTO category : dto.getCategories()) {
            Category cat = catRepository.getReferenceById(category.getId());
            product.getCategories().add(cat);
        }
    }
}
