package com.payatak.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payatak.authservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String username);

}
