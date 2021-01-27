package com.briebug.swag;

import com.briebug.swag.models.Product;
import com.briebug.swag.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository product_repo;

    public DataLoader(ProductRepository pr) {
        product_repo = pr;
    }

    @Override
    public void run(String [] args) throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Stickers", 3.99f, 2));
        products.add(new Product("Hats", 10.99f, 5));
        products.add(new Product("Shirts", 20.98f, 10));

        product_repo.saveAll(products);
    }
}
