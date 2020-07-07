package com.memmana.service;
import java.util.List;

/*
 * 服务层接口
 */
import com.memmana.entity.User;

public interface UserService {
	
	public User queryUserById(Long id);
	
	List<User> getAllUser();
	
	void addUser(User user);
	
	void deleteUser(Long id);
	
	void update(User user);

	Long checkLogin(User user);



}