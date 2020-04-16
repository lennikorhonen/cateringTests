package com.example.cateringProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public String company;
	public String firstName;
	public String lastName;
	public String mail;
	public String address;
	public int amount;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "productid")
	private Product product;
	
	public Customer() {
		super();
	}
	
	public Customer(String company, String firstName, String lastName, String mail, String address,
			int amount ,Product product) {
		super();
		this.company = company;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.address = address;
		this.amount = amount;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
