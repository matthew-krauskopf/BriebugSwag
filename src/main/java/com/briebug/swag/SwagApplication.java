package com.briebug.swag;

import com.briebug.swag.models.Product;
import com.briebug.swag.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SwagApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwagApplication.class, args);
	}

	public void printCatalog(ProductRepository repo) {
		System.out.println("************Start************************");
		for (Product ans : repo.findAll()) {
			System.out.println(ans.toString());
		}
		System.out.println("************End**************************");
	}
}
