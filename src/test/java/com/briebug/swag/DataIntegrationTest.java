package com.briebug.swag;

import com.briebug.swag.models.Product;
import com.briebug.swag.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DataIntegrationTest {

    @Autowired
    ProductRepository product_repo;

    @Test
    public void getAllProductsTest() {
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("Hats", 3.99f, 2));
        catalog.add(new Product("Shirts", 20.00f, 5));
        product_repo.saveAll(catalog);

        List<Product> pulled = product_repo.findAll();
        assertThat(catalog).isEqualTo(pulled);
    }

    @Test
    public void getByNameTest() {
        Product fsticks = new Product("Fishsticks", 2.99f, 100);
        product_repo.save(fsticks);
        Product pulled = product_repo.findByName(fsticks.getName());
        assertThat(fsticks.equals(pulled)).isEqualTo(true);
    }

    @Test
    public void updateStockTest() {
        Product hats = new Product("Hats", 3.99f, 2);
        product_repo.save(hats);
        Product bought = product_repo.findByName("Hats");
        bought.setStock(bought.getStock()-1);
        product_repo.save(bought);
        Product check = product_repo.findByName("Hats");
        assertThat(check.getStock()).isEqualTo(1);
    }

    @Test
    public void deleteProductTest() {
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("Hats", 3.99f, 2));
        catalog.add(new Product("Shirts", 20.00f, 5));
        product_repo.saveAll(catalog);

        product_repo.delete(product_repo.findByName("Shirts"));
        List<Product> pulled = product_repo.findAll();
        assertThat(catalog).isNotEqualTo(pulled);
    }
}
