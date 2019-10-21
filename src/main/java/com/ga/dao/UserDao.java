package com.ga.dao;

import java.util.List;

import com.ga.entity.User;

public interface UserDao {
	
	public List<User> listUsers();
	
	public User signup(User user);
	
	public User login(User user);
	
	public User updateUser(User user, Long userId);
	
	public Long deleteUser(Long userId);
	
	public User getUserByUsername(String username);
	
	public User addCourse(String username, int courseId);

}