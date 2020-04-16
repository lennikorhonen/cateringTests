package com.example.cateringProject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.cateringProject.domain.Customer;
import com.example.cateringProject.domain.CustomerRepository;
import com.example.cateringProject.domain.ProductRepository;

@Controller
public class CustomerController {
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private ProductRepository prepository;
	
	@RequestMapping(value = "/customerlist", method = RequestMethod.GET)
	public String getCustomers(Model model) {
		model.addAttribute("customers", repository.findAll());
		return "customerlist";
	}
	
	//Mappaus add toiminnolle default metodilla(GET)
		@RequestMapping(value = "/addcustomer")
		public String addCustomer(Model model) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("product", prepository.findAll());
			return "addcustomer";
		}

		//Mappaus muokkaa toiminnolle default metodilla(GET)
		@RequestMapping(value="/editcustomer/{id}")
		@PreAuthorize("hasAuthority('ADMIN')")
		public String editCustomer(@PathVariable("id") Long customerId, Model model) {
			model.addAttribute("customer", repository.findById(customerId));
			model.addAttribute("product", prepository.findAll());
			return "editcustomer";
		}

		//Mappaus tallenna toiminnolle jotta muokatessa tai lisätessä voidaan tallentaa lisäykset
		@RequestMapping(value="/savecustomer", method = RequestMethod.POST)
		public String save(Customer customer) {
			repository.save(customer);
			return "redirect:customerlist"; //käytetään redirectiä, koska ei ole /save/customerlist polkua
		}

		//Mappaus poisto toiminnolle get metodilla
		@RequestMapping(value="/deletecustomer/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('ADMIN')")
		public String deleteCustomer(@PathVariable("id") Long customerId, Model model) {
			repository.deleteById(customerId);
			return "redirect:../customerlist"; //Käytetään redirectiä, koska ei ole /delete/{id}/customerlist polkua
		}
}
