package com.example.cateringProject.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

//luodaan kategoria luokka
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Kategorian tietokanta id on automaattisesti luotu
	public Long categoryId; 
	private String name;
	
	@JsonBackReference //RESTful palvelun tietokanta viite
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="category") //Kerrotaan softalle, että tällä luokalla on yksi moneen yhteys
	private List<Product> product;
	
	//luokan super metodit
	
	public Category() {
		super();
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	//Luokan getterit ja setterit kategorioiden luomista ja lukemista varten
	
	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
