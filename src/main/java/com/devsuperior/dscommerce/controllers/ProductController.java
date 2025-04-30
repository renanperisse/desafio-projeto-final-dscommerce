package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dtos.ProductMinDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService productService) {
        service = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductMinDTO>> getAll(@RequestParam(name = "name", defaultValue = "") String name,
                                                      @PageableDefault(size = 12, sort = "name", direction = Sort.Direction.ASC)
                                                      Pageable pageable) {
        Page<ProductMinDTO> products = service.findAll(name, pageable);
        return ResponseEntity.ok(products);

    }

}
