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

	public void printCatalog(ProductRepository repo) {
		System.out.println("************Start************************");
		for (Product ans : repo.findAll()) {
			System.out.println(ans.toString());
		}
		System.out.println("************End**************************");
	}
}
