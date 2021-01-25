package com.briebug.swag.services;

import com.briebug.swag.models.Product;
import com.briebug.swag.repositories.ProductRepository;
import com.briebug.swag.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository product_repo;

    public ProductServiceImpl(ProductRepository product_repo) {
        this.product_repo = product_repo;
    }

    public List<Product> list() {
        return product_repo.findAll();
    }

    public void create(Product product) {
        product_repo.save(product);
    }

    @Override
    public void update(Product product, Long id) {
        if (product_repo.findById(id).isPresent()) {
            product_repo.save(product);
        } else throw new ResourceNotFoundException();
    }

    public void save(Product product) {
        product_repo.save(product);
    }

    public Optional<Product> get(Long id) {
        return product_repo.findById(id);
    }

    public void delete(Long id) {
        if (product_repo.findById(id).isPresent()) {
            product_repo.deleteById(id);
        }
        else throw new ResourceNotFoundException();
    }
}
