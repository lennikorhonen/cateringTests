package com.example.cateringProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cateringProject.domain.Category;
import com.example.cateringProject.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class CatRepoTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void findByCategoryShouldReturnProduct() {
		List<Category> category = repository.findByName("Kaffepaku");
		assertThat(category).hasSize(1);
		assertThat(category.get(0).getCategoryId()).isEqualTo(2);
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Kahvila");
		repository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}

}
