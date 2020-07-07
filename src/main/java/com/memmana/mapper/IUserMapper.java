package com.memmana.mapper;
/*
 * 映射接口文件处于DAO层
 */

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.memmana.entity.User;

public interface IUserMapper {

	@Insert("insert into into test_user(username,password,email) value(#{username},#{password},#{email})")
	public void addUser(User user);
	
	@Delete("delete from test_user where id=#{id}")
	public void deleteUser(Long id);
	
	@Update("update test_user set password=#{password},email=#{email},username=#{username} where id=#{id}")
	public void updatePSWAndEmbyName(User user);
	
	@Select("select id,username,password,email from test_user")
	public List<User> getUserList();
	
	@Select("select username,password,email from test_user where id=#{id}")
	public User getUserbyId(Long id);
	
	@Select("select id from test_user where username=#{username} and password=#{password}")
	public Long findUserByUsernameAndPassword(User user);
}
