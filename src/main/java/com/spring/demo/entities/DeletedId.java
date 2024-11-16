package com.spring.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeletedId {

    @Id
    private Long id;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

