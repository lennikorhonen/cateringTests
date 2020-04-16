package com.example.cateringProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cateringProject.domain.Category;
import com.example.cateringProject.domain.Product;
import com.example.cateringProject.domain.ProductRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
class ProdRepoTest {
	
	@Autowired
	private ProductRepository repository;
	
	@Test
	public void findByNameShouldReturnProduct() {
		List<Product> products = repository.findByName("Pulla");
		assertThat(products).hasSize(1);
		assertThat(products.get(0).getPrice()).isEqualTo(2.00);
	}
	
	@Test
	public void createNewProd() {
		Product product = new Product("Nakki", 5, new Category("vene"));
		repository.save(product);
		assertThat(product.getId()).isNotNull();
	}

}
