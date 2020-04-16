package com.example.cateringProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cateringProject.domain.Category;
import com.example.cateringProject.domain.Customer;
import com.example.cateringProject.domain.CustomerRepository;
import com.example.cateringProject.domain.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
class CustomerRepoTest {
	@Autowired
	private CustomerRepository repository;
	
	@Test
	public void findByCompanyShouldReturnCustomer() {
		List<Customer> customers = repository.findByCompany("Faijan leivät");
		assertThat(customers).hasSize(1);
		assertThat(customers.get(0).getLastName()).isEqualTo("Meikäläinen");
	}
	
	@Test
	public void createNewCustomer() {
		Customer customer = new Customer("Naken nakit", "Nakke", "Nakuttaja", "nakke@nakuttaja.fi", "Nakuttajantie 2",
				20, new Product("Nakki", 3, new Category("Kauppa")));
		repository.save(customer);
		assertThat(customer.getId()).isNotNull();
	}
	
}
