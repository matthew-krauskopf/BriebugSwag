package com.briebug.swag;

import com.briebug.swag.models.Product;
import com.briebug.swag.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.Optional;

@SpringBootApplication
public class SwagApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwagApplication.class, args);
	}

	//@Autowired
	//ProductRepository products;

	@Bean
	public CommandLineRunner demo (ProductRepository products) {
		return (args) -> {
			Product whizz = new Product(4, "CheezeWizz", 3.99f, 2);
			Product hat = new Product(2, "Hats", 10.99f, 5);
			printCatalog(products);
			products.save(whizz);
			printCatalog(products);
			products.save(hat);
			printCatalog(products);
			products.delete(hat);
			printCatalog(products);

			Product bought = products.findByName(whizz.getName());
			bought.setStock(bought.getStock()-1);
			products.save(bought);
			printCatalog(products);

		};
	}

	public void printCatalog(ProductRepository repo) {
		System.out.println("************Start************************");
		for (Product ans : repo.findAll()) {
			System.out.println(ans.toString());
		}
		System.out.println("************End**************************");
	}
}
