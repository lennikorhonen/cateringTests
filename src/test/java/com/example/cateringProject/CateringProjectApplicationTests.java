package com.example.cateringProject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cateringProject.web.ProductController;

@RunWith(SpringRunner.class)
@SpringBootTest
class CateringProjectApplicationTests {
	
	@Autowired
	private ProductController controller;

	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}
}
