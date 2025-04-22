package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bway.springproject.model.User;
import com.bway.springproject.service.UserService;
//import com.mysql.cj.log.Log;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/login" })
	public String getLogin() {
		return "LoginForm";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session) {
		String hashedPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		User u = userService.userLogin(user.getUsername(), hashedPassword);
		//today i learned about http session which worked as guard in case of gallery route
		if (u != null) {
			
			log.info("-------login success------");
			session.setAttribute("activeUser", u);
			session.setMaxInactiveInterval(500);
//			model.addAttribute("fname",u.getFname());
			return "Home";
		}
		log.info("-------login failed------------");
		model.addAttribute("message", "Invalid Username Password!!!");
		return "LoginForm";
	}

	@GetMapping("/signup")
	public String getSignup() {
		return "SignupForm";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user, Model model) {

		// check user if already exist
		if (userService.FindByUserName(user.getUsername()) != null) {
			model.addAttribute("message", "User already exists!");
			return "SignupForm";
		}
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

		userService.userSignUp(user);

		return "LoginForm";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		log.info("-----------logout success--------------");
		session.invalidate(); // session kill
		return "LoginForm";
	}

	@GetMapping("/profile")
	public String getProfile() {
		return "Profile";
	}
}
