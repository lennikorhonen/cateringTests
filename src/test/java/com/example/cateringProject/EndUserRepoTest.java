package com.example.cateringProject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cateringProject.domain.EndUser;
import com.example.cateringProject.domain.EndUserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class EndUserRepoTest {
	@Autowired
	private EndUserRepository repository;
	
	@Test
	public void findByUsernameShouldReturnRole() {
		EndUser users = repository.findByUsername("admin");
		assertThat(users.getRole()).isEqualTo("ADMIN");
	}
	
	@Test
	public void createNewUser() {
		EndUser user = new EndUser("quest", "$2y$12$RSANDzlSfObxYbTv8/iXeOr.ZgZjzo28d9aKynTtu4pCPxU4zHAwC", "QUEST");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
}
