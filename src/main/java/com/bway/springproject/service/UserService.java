package com.bway.springproject.service;

import com.bway.springproject.model.User;

public interface UserService {
	void userSignUp(User user);
	User userLogin(String username, String password);
	User FindByUserName(String username);
}
