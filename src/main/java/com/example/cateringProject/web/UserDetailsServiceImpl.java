package com.example.cateringProject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cateringProject.domain.EndUser;
import com.example.cateringProject.domain.EndUserRepository;

//tätä luokkaa käytetään käyttäjän autentikointiin ja sen oikeuksien valtuuttamiseen
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private final EndUserRepository repository;
	@Autowired
	public UserDetailsServiceImpl(EndUserRepository userRepository) {
		this.repository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EndUser curruser= repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}