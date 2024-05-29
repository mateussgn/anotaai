package com.mateussgn.anota_ai.domain.product;

import com.mateussgn.anota_ai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String ownerId;
    private String description;
    private Integer price;
    private Category category;
}
