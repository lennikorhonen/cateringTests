package com.example.cateringProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//CRUD repositorio kategoria luokkaa varten
public interface ProductRepository extends CrudRepository<Product, Long>{
	List<Product> findByName(String name); //Kysely metodi käyttöliittymälle

}
