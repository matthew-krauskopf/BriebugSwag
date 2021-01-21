package com.briebug.swag.controllers;

import com.briebug.swag.models.Product;
import com.briebug.swag.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Product get(@PathVariable Long id) {
        return productRepository.getOne(id);
    }

    @PostMapping
    public Product create(@RequestBody final Product product) {
        return productRepository.saveAndFlush(product);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productRepository.getOne(id);
        BeanUtils.copyProperties(product, existingProduct, "id");
        return productRepository.saveAndFlush(existingProduct);
    }

}
