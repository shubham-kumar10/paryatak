package com.paryatak.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paryatak.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String username);

}
