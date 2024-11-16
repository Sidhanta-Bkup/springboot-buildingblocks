package com.spring.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.entities.DeletedId;

public interface DeletedIdRepository extends JpaRepository<DeletedId, Long>{

}
