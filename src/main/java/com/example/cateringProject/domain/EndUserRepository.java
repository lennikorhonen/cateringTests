package com.example.cateringProject.domain;

import org.springframework.data.repository.CrudRepository;

//CRUD repositorio käyttäjiä varten
public interface EndUserRepository extends CrudRepository<EndUser, Long>{
	EndUser findByUsername(String username); //Kysely metodi käyttöliittymälle
}
