package com.example.cateringProject.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.cateringProject.domain.EndUser;
import com.example.cateringProject.domain.EndUserRepository;
import com.example.cateringProject.domain.SignupForm;

@Controller
public class UserController {
	@Autowired
	private EndUserRepository repository;
	
	@RequestMapping(value = "signup")
	public String addEndUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			if(signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
				String pwd =signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);
				
				EndUser newUser = new EndUser();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				
				if(repository.findByUsername(signupForm.getUsername()) == null) {
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "User name already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("password", "err.password", "Password does not match");
				return "signup";
			}
		}
		return "redirect:/login";
	}
}
