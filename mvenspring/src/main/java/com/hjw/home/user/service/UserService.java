package com.hjw.home.user.service;

import java.util.List;

import com.hjw.home.user.User;

public interface UserService {
	public void saveuser(User user) throws RuntimeException;
	
	public List<User> getalluser();

}
