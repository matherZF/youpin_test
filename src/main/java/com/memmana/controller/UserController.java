package com.memmana.controller;
/*
 * 控制层控制器，调用服务层
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.memmana.entity.User;
import com.memmana.service.UserService;
import com.memmana.util.JWTUtil;
import com.memmana.util.ResponseData;

import net.sf.json.JSONArray;


@Controller
public class UserController {
	
	private static final char[] to = null;
	@Autowired
	private UserService userService;  //注入服务层对象
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	/**
	 * 用户登录
	 * @Title: login   
	 * @Description: TODO
	 * @param: @param user
	 * @param: @return      
	 * @return: ResponseData      
	 * @throws
	 */
    @RequestMapping(value="login",produces="application/json")
    public ResponseData login(String username,String password) {
    	//模拟去查询数据库，看是否存在此用户
    	User user = new User(username, password);
    	Long id = userService.checkLogin(user);
    	ResponseData responseData = ResponseData.ok();
    	if(!(id==null||"".equals(id))) {
    		//生成token
    		String token = JWTUtil.generToken(String.valueOf(id), "mather", user.getEmail());		
    		
    		//向浏览器返回token，客户端受到此token后存入cookie中，或者h5的本地存储中
    		responseData.putDataValue("token", token);
    		//以及用户
    		responseData.putDataValue("user", user);
    	}else {
    		//用户或者密码错误
    		responseData=ResponseData.customerError();
    	}
    	return responseData;
    }
    



	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getUser(@PathVariable Long id) {
		JSONArray jsonArray = new JSONArray();
		User user =new User();
		user=userService.queryUserById(id);
		jsonArray.add(user);
		return jsonArray;
	}
	
	@RequestMapping(value="/userlist",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getAllUser() {
		JSONArray jsonObject = new JSONArray();
		List<User> userList = userService.getAllUser();
		jsonObject.add(userList);
		return jsonObject;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public JSONArray deleteUser(@PathVariable Long id) {
		JSONArray jsonObject = new JSONArray();
		userService.deleteUser(id);
		List<User> userList = userService.getAllUser();
		jsonObject.add(userList);
		return jsonObject;
	}
	
	@RequestMapping(value="update/{id}/{username}/{password}/{email}",method=RequestMethod.PUT)
	@ResponseBody
	public JSONArray deleteUser(@PathVariable Long id,@PathVariable String username,@PathVariable String password,@PathVariable String email) {
		User user = new User(id,username,password,email);
		JSONArray jsonObject = new JSONArray();
		userService.update(user);
		List<User> userList = userService.getAllUser();
		jsonObject.add(userList);
		return jsonObject;
	}
	
	@RequestMapping(value="/add/{username}/{password}/{email}",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public JSONArray addUser(@PathVariable String username,@PathVariable String password,@PathVariable String email) {
		User user = new User(username,password,email);
		JSONArray jsonObject = new JSONArray();
		userService.addUser(user);
		List<User> userList = userService.getAllUser();
		jsonObject.add(userList);
		return jsonObject;
	}
	
	
	
	
	
	
	
	
	
	
	
}
