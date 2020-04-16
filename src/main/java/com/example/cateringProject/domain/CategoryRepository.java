package com.example.cateringProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//CRUD repositorio kategoria luokkaa varten
public interface CategoryRepository extends CrudRepository<Category, Long>{
	List<Category> findByName(String name); //Kysely metodi käyttöliittymälle
}
