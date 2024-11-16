package com.spring.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.demo.controller.UserExistsException;
import com.spring.demo.entities.DeletedId;
import com.spring.demo.entities.Users;
import com.spring.demo.exceptions.UserNotFoundException;
import com.spring.demo.repositories.DeletedIdRepository;
import com.spring.demo.repositories.UsersRepo;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepo repo;
	@Autowired
	private DeletedIdRepository deletedIdRepo;
	
	public List<Users> getAllUsers() {
		return repo.findAll();
	}
	
	public Users createUser(Users users, Long reuseId) throws UserExistsException{
		
		Users existingUser =
			repo.findByUsername(users.getUsername());
		
		if(existingUser != null) {
			throw new UserExistsException(
					"User already exists in repo");
		}
		
        if (reuseId != null && deletedIdRepo.existsById(reuseId)) {
            // Reuse the provided ID
            users.setId(reuseId);
            deletedIdRepo.deleteById(reuseId); // Remove the ID from the deleted table
        }
		
		return repo.save(users);
	}
	
	public Optional<Users> getUserById(Long id) throws UserNotFoundException {
		Optional<Users> user = repo.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in repo...");
		}
		
		return user;
	}
	
	public Users updateuserById( Long id,
				Users users) throws UserNotFoundException {
		
		Optional<Users> optUser = repo.findById(id);
		
		if(!optUser.isPresent()) {
			throw new UserNotFoundException("User not found in repo, provide correct user id");
		}
		
		users.setId(id);
		return repo.save(users);
	}
	
	public void deleteUserById(Long id) throws UserNotFoundException {
		if(!repo.findById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
						"User not found in repo, provide correct user id");
		}
		
		repo.deleteById(id);
        // Save the deleted ID to the database
        DeletedId deletedId = new DeletedId();
        deletedId.setId(id);
        deletedIdRepo.save(deletedId);
	}
	
    // Get the list of deleted IDs
    public List<Long> getDeletedIds() {
        return deletedIdRepo.findAll().stream()
                .map(DeletedId::getId)
                .collect(Collectors.toList());
    }
	
	public Users getUserByUserName(String username) {
		return repo.findByUsername(username);
	}
	

}
