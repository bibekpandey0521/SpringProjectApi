package com.bway.springproject.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.User;
import com.bway.springproject.repository.UserRepository;
import com.bway.springproject.service.UserService;

@Service //use to apply business logic to data 
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public void userSignUp(User user) {
		userRepository.save(user);
	}

	@Override
	public User userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	public User FindByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	
	
}

