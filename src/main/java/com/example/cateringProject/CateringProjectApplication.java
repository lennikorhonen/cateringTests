package com.example.cateringProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.cateringProject.domain.Category;
import com.example.cateringProject.domain.CategoryRepository;
import com.example.cateringProject.domain.Customer;
import com.example.cateringProject.domain.CustomerRepository;
import com.example.cateringProject.domain.Product;
import com.example.cateringProject.domain.ProductRepository;
import com.example.cateringProject.domain.EndUser;
import com.example.cateringProject.domain.EndUserRepository;

@SpringBootApplication
public class CateringProjectApplication {
	//Alustetaan loggeri
	private static final Logger log = LoggerFactory.getLogger(CateringProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CateringProjectApplication.class, args);
	}
	
	//Luodaan muutama esimerkki tuote, tuotteiden kategoriat sekä pari esimerkki käyttäjää
	@Bean
	public CommandLineRunner exampleProduct(ProductRepository repository, CategoryRepository crepository, EndUserRepository urepository, CustomerRepository curepository) {
		return(args) ->{
			
			//Koska käytämme postgre tietokantaa täytyy käynnistäessä ensin poistaa esimerkki tiedot
			//ja sen jälkeen lisätä ne uudestaan
			repository.deleteAll();
			crepository.deleteAll();
			curepository.deleteAll();
			urepository.deleteAll();
			
			log.info("save a couple of categories");
			crepository.save(new Category("Catering"));
			crepository.save(new Category("Kaffepaku"));
			//Hyvät ideat on yrityksen työntekijälle joka voi matkalla ollessaan lisätä tuotteita ja kokeilla
			//niitä kun ei ole enää tien päällä
			crepository.save(new Category("Hyvät ideat"));
			
			System.out.println("Kategoriat lisätty");
			
			log.info("save a couple of products");
			repository.save(new Product("Pulla", 2.00, crepository.findByName("Kaffepaku").get(0)));
			repository.save(new Product("Kakku pala", 3.50, crepository.findByName("Catering").get(0)));
			repository.save(new Product("Pasteija", 2.00, crepository.findByName("Hyvät ideat").get(0)));
			repository.save(new Product("Catering paketti 1", 75, crepository.findByName("Catering").get(0)));
			repository.save(new Product("Catering paketti 2", 125, crepository.findByName("Catering").get(0)));
			repository.save(new Product("Catering paketti 3", 140, crepository.findByName("Catering").get(0)));
						
			System.out.println("Tuotteet lisätty");
			
			log.info("save couple example customers");
			curepository.save(new Customer("Faijan leivät", "Matti" , "Meikäläinen", "matti.meikalainen@faija.fi",
					"Leipätie 5", 1, repository.findByName("Catering paketti 3").get(0)));
			curepository.save(new Customer("Mutsin kakut", "Maija" , "Moilanen", "maija.moilanen@mutsi.fi",
					"Kakkutie 7", 1, repository.findByName("Catering paketti 1").get(0)));
			
			log.info("save a couple of users");
			urepository.save(new EndUser("user", "$2y$12$UY92f4lCumIk90xY6Z9ZMODKPmm8sOG5cVfAnNEeMEocvTv0oMN7C",
					"USER"));
			urepository.save(new EndUser("admin", "$2y$12$5piFyCJx6vDwNTDuk.6FWe0rLgzz0C1sD2h/RsqS1/YCDM7rpGMwW",
					"ADMIN"));
			
			System.out.println("Käyttäjät lisätty");
			
			//Logataan konsoliin tuotteista pieni teksti pätkä
			log.info("Fetch all products");
			for (Product product : repository.findAll()) {
				log.info(product.toString());
			}
			
		};
	}

}
