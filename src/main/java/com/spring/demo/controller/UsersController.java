package com.spring.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.demo.entities.Users;
import com.spring.demo.exceptions.UserNotFoundException;
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
			Users users,
			@RequestParam(required = false) Long reuseId) {
		try {
			return service.createUser(users, reuseId);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,
					e.getMessage());
		}
		
	}

	@GetMapping("/users/{id}")
	public Optional<Users> getUserById(@PathVariable Long id) {
		try {
			return service.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						e.getMessage());
		}
		
	}
	
	@PutMapping("/users/{id}")
	public Users updateuserById(@PathVariable
			Long id, @RequestBody Users users)  {
		
		try {
			return service.updateuserById(id, users);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						e.getMessage());
		}
		
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		service.deleteUserById(id);
	}
	
    // Get the list of deleted IDs
    @GetMapping("/deleted-ids")
    public List<Long> getDeletedIds() {
        return service.getDeletedIds();
    }
	
	@GetMapping("/users/byusername/{username}")
	public Users getUserByUserName(@PathVariable
				String username) {
		return service.getUserByUserName(username);
	}
	
}
