package com.spring.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.entities.Users;
import com.spring.demo.services.UsersService;

@RestController
@RequestMapping("/api")
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return service.getAllUsers();
	}
	
	@PostMapping("/create-user")
	public Users createUser(@RequestBody
			Users users) {
		return service.createUser(users);
	}

	@GetMapping("/users/{id}")
	public Optional<Users> getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}
	
	@PutMapping("/users/{id}")
	public Users updateuserById(@PathVariable
			Long id, @RequestBody Users users)  {
		return service.updateuserById(id, users);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		service.deleteUserById(id);
	}
	
	@GetMapping("/users/byusername/{username}")
	public Users getUserByUserName(@PathVariable
				String username) {
		return service.getUserByUserName(username);
	}
	
}
