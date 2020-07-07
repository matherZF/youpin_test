 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Login</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.js"></script> 

</head>
<body>


<div style="width: 300px;height: 200px;margin: 20px auto;">
	<table>
		<tr>
			<td></td>
			<td><p>登录<p></td>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><input type="text" id="username"></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input type="password" id="password"></td>
		</tr>
		<tr>
			<td></td>
			<td><button onclick="check()">登录</button></td>
		</tr>	
	</table>

	
	<script type="text/javascript">
	function check() {
		alert("aaa");
		var username=$('#username').val();
		var password=$('#password').val();
		alert(username+password);
		$.ajax({
		    type: "post",
		    url:"login",
		    datatype:"json",
		    data: {
		    	username:username,
		    	password:password
		    },
		    success: function (result) {
				if(result.code==200){
					//保存token用来判断用户是否登录，和身份是否属实
					$.cookie('token', result.data.token);
					//$.cookie('user', result.data.user);
					//转向主页面
					location="userlist";
				}else{
					alert("用户名或者密码错误！");
				}
		    }
		});
	}
		
	
</script>
</body>

</html>