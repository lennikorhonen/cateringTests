package com.example.cateringProject.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//Luodaan Tuote luokka
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //Tuotteiden tietokanta id on automaattisesti luotu
	public Long id;
	public String name;
	public double price;
	
	@ManyToOne
	@JsonManagedReference //RESTful palvelun tietokanta viite
	@JoinColumn(name = "categoryid") //Monta yhteen tietokanta yhteys tuotteiden ja kategorioiden välille
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	@JsonBackReference
	private List<Customer> customer;
	
	//Luokan super metodit
	
	public Product () {
		super();
	}
	
	public Product (String name, double price, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	//getterit ja setterit tuotteiden lisäämistä ja lukemista varten

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	//toString metodi debuggausta varten

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
