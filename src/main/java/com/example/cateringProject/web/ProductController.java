package com.example.cateringProject.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cateringProject.domain.CategoryRepository;
import com.example.cateringProject.domain.Product;
import com.example.cateringProject.domain.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository crepository;

	//Mappaus productlistille get metodilla
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public String getProducts(Model model) {
		model.addAttribute("products", repository.findAll());
		return "productlist";
	}

	//Mappaus add toiminnolle default metodilla(GET)
	@RequestMapping(value = "/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", crepository.findAll());
		return "addproduct";
	}

	//Mappaus muokkaa toiminnolle default metodilla(GET)
	@RequestMapping(value="/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", repository.findById(productId));
		model.addAttribute("category", crepository.findAll());
		return "editproduct";
	}

	//Mappaus tallenna toiminnolle jotta muokatessa tai lisätessä voidaan tallentaa lisäykset
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Product product) {
		repository.save(product);
		return "redirect:productlist"; //käytetään redirectiä, koska ei ole /save/productlist polkua
	}

	//Mappaus poisto toiminnolle get metodilla
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable("id") Long productId, Model model) {
		repository.deleteById(productId);
		return "redirect:../productlist"; //Käytetään redirectiä, koska ei ole /delete/{id}/productlist polkua
	}									  //kaksi pistettä on se takia, että päästään /productlist polkuun

	//RESTful service kaikkien tuotteiden hakua varten
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody List<Product> productListRest() {
		return (List<Product>) repository.findAll();
	}

	//RESTful service yksittäisen tuotteen hakua varten id:n perusteella
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Product> findProductRest(@PathVariable("id") Long productId) {
		return repository.findById(productId);
	}
}
