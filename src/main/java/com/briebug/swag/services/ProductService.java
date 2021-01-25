package com.briebug.swag.services;

import com.briebug.swag.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> list();

    void create(Product product);

    void save(Product product);

    Optional<Product> get(Long id);

    void update(Product product, Long id);

    void delete(Long id);
}
