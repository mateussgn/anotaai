package com.mateussgn.anota_ai.repository;

import com.mateussgn.anota_ai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
