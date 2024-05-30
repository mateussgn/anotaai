package com.mateussgn.anota_ai.controllers;

import com.mateussgn.anota_ai.domain.product.Product;
import com.mateussgn.anota_ai.domain.product.ProductDTO;
import com.mateussgn.anota_ai.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productDTO) {
        Product newProduct = this.service.insert(productDTO);

        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = this.service.getAll();

        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = this.service.update(id, productDTO);

        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable String id) {
        this.service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
