package com.spring.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.demo.entities.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>{
	
	Users findByUsername(String username);
	
}
