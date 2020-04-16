package com.example.cateringProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.cateringProject.web.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//Injektoidaan luokka UserDetailsServiceImpl
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/css/**").permitAll() //css käytössä vaikka ei ole kirjautunut
		.and()
		.authorizeRequests().antMatchers("/", "/error" ,"/signup", "/saveuser").permitAll()
		.and()
		.authorizeRequests().anyRequest().authenticated()
		.and()
	.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/productlist") //Onnistuneessa kirjautumisessa ohjataan suoraan productlist sivulle
			.permitAll()
			.and()
	.logout()
		.permitAll()
		.invalidateHttpSession(true); //Kirjautuessa ulos mitätöidään http istunto
	}
	
	//Muunnetaan salasana koodikielelle
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new
				BCryptPasswordEncoder());
	}
}
