<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>登陆页面</title>
</head>
<body bgcolor="white"><p>
			
	<form action="login.action" method="post" >
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="user.username"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="user.password"></td>
			</tr>
			<tr>
				<td><input type="reset" value="重填"></td>
				<td><input type="submit" value="登陆"></td>
			</tr>
		</table>
	</form><br>
	
</body>
</html>