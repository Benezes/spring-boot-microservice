package com.mnz.product.repository;

import com.mnz.product.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<ProductModel, UUID> {
}
