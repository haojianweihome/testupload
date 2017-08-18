package com.hjw.home.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hjw.home.user.User;
@Repository
public interface UserMapper {
	public void saveuser(User user);
	
	public List<User> getalluser();
}
