package com.mateussgn.anota_ai.services;

import com.mateussgn.anota_ai.domain.category.Category;
import com.mateussgn.anota_ai.domain.category.exceptions.CategoryNotFoundException;
import com.mateussgn.anota_ai.domain.product.exceptions.ProductNotFoundException;
import com.mateussgn.anota_ai.domain.product.Product;
import com.mateussgn.anota_ai.domain.product.ProductDTO;
import com.mateussgn.anota_ai.repository.ProductRepository;
import com.mateussgn.anota_ai.services.aws.AwsSnsService;
import com.mateussgn.anota_ai.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CategoryService categoryService;

    private ProductRepository repository;

    private AwsSnsService awsSnsService;

    public ProductService(ProductRepository repository, CategoryService categoryService, AwsSnsService awsSnsService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.awsSnsService = awsSnsService;
    }

    public Product insert(ProductDTO productDTO) {
        Category category = this.categoryService.getById(productDTO.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productDTO);
        newProduct.setCategory(category);

        this.repository.save(newProduct);

        this.awsSnsService.publish(new MessageDTO(newProduct.getOwnerId()));

        return newProduct;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Product update(String id, ProductDTO productDTO) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if (productDTO.categoryId() != null) {
            this.categoryService.getById(productDTO.categoryId())
                    .ifPresent(product::setCategory);
        }

        if (productDTO.title().isEmpty()) {
            product.setTitle(product.getTitle());
        }

        if(!productDTO.description().isEmpty()) {
            product.setDescription(productDTO.description());
        }

        if(!(productDTO.price() == null)) {
            product.setPrice(productDTO.price());
        }

        this.repository.save(product);

        this.awsSnsService.publish(new MessageDTO(product.getOwnerId()));

        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);
    }
}
