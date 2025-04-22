package com.bway.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	//for user login
	User findByUsernameAndPassword(String username,String password);
	 User findByUsername(String username);
}
