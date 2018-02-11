package com.example.jpain10steps.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpain10steps.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
