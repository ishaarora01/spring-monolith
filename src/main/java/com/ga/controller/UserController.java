package com.ga.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.JwtResponse;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!";
    }

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public List<User> listUsers() {
		return userService.listUsers();
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
    	return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }
	
	@PutMapping("/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Long userId) {
		return userService.updateUser(user, userId);
	}

	@DeleteMapping("/{userId}")
	public Long deleteUser(@PathVariable Long userId) {
		return userService.deleteUser(userId);
	}
	
	@PutMapping("/{username}/course/{courseId}")
    public User addCourse(@PathVariable String username, @PathVariable int courseId) {
        return userService.addCourse(username, courseId);
    }
}
