<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
<style>
 body{
 	font-size:12px;
 	background-color:#eff2fb;
 	}
.tb{
	width:350px;
	height:161px;
	margin-left:50px;
	margin-top:50px;
	border-style:1px solid #99CCFF;
	
	}
</style>
<script>
function check(){
	var hidpwd=document.getElementById("hidold").value;	//隐藏输入框中的旧密码值
	var oldpwd=document.getElementById("old").value;	//旧密码输入框的值
	var newpwd=document.getElementById("password").value;
	if(hidpwd != oldpwd){
		alert("输入的旧密码不正确");
		return false;
	}else if(newpwd == "" || (newpwd.length==0)){
		alert("请输入密码");
		return false;
	}else if(newpwd==oldpwd){
		alert("新密码不能与旧密码相同");
		return false;
	}else{
		return true;
	}
}
</script>
</head>
<body>
	<table class="tb">
		<form action="${pageContext.request.contextPath }/userManage" method="post" onSubmit="return check()">
		<tr>
			<td>用户名：</td>
			<td><c:out value="${user.username }"></c:out>
			<input type="hidden" name="id" value="${user.id }">
			</td>
		</tr>
		
		<tr>
			<td>旧密码：</td>
			<td>
			<input type="hidden" name="hidold" value="${user.password }" id="hidold">
			<input type="password" name="old" id="old">
			</td>
		</tr>
		
		<tr>
			<td>新密码：</td>
			<td>
			<input type="password" name="password" id="password">
			<input type="hidden" name="action" value="update">
			</td>
		</tr>
		
		<tr>
		<td colspan="2" align="center">
		<input type="submit" value="修改">
		<input type="reset" value="重置">
		</td>
		</tr>
		</form>
		
	</table>
</body>
</html>