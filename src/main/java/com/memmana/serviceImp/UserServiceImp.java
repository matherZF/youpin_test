package com.memmana.serviceImp;
/*
 * 服务层的实现类，调用DAO层
 * 实现类类名可以任意（组件扫描只要求能扫描到服务接口的实现方法）
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memmana.entity.User;
import com.memmana.mapper.IUserMapper;
import com.memmana.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private IUserMapper userDao;  //注入DAO层对象
	
	public User queryUserById(Long id) {
		return userDao.getUserbyId(id);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userDao.deleteUser(id);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.updatePSWAndEmbyName(user);
	}

	@Override
	public Long checkLogin(User user) {
		// TODO Auto-generated method stub
		return userDao.findUserByUsernameAndPassword(user);
		
		
	}

	

}
