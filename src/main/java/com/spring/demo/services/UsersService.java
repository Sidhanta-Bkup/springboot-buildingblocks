package com.spring.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.entities.Users;
import com.spring.demo.repositories.UsersRepo;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepo repo;
	
	public List<Users> getAllUsers() {
		return repo.findAll();
	}
	
	public Users createUser(Users users) {
		return repo.save(users);
	}
	
	public Optional<Users> getUserById(Long id) {
		Optional<Users> user = repo.findById(id);
		return user;
	}
	
	public Users updateuserById( Long id,
				Users users) {
		users.setId(id);
		return repo.save(users);
	}
	
	public void deleteUserById(Long id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
		}
	}
	
	public Users getUserByUserName(String username) {
		return repo.findByUsername(username);
	}
	

}
