package com.devsuperior.dscommerce.dtos;

import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres.")
    private String name;

    @Size(min = 10, message = "A descrição precisa ter, no mínimo, 10 caracteres.")
    private String description;

    @NotNull(message = "Campo requerido.")
    @Positive(message = "O campo preço deve ser positivo.")
    private Double price;

    private String imgUrl;

    @Size(min = 1, message = "O produto deve ter pelo menos uma categoria.")
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
        for (Category category : product.getCategories()) {
            categories.add(new CategoryDTO(category));
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
