<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.hbsi.dao.UserDao"
    %>
    <%@ page
    import="com.hbsi.dao.impl.UserDaoImpl"
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户名检测</title>
</head>
<body>
	<jsp:useBean id="user" class="com.hbsi.bean.User"></jsp:useBean>
	<jsp:setProperty name="user" property="username" param="username"/>
	<%
	UserDao ud=new UserDaoImpl();
	boolean flag=ud.checkUsername(user.getUsername());
	String uservalidate="no";
	request.setAttribute("uname", user.getUsername());
	if (flag){
		request.setAttribute("usermessage", "用户名已经被注册，请重新输入");
	}else{
			request.setAttribute("usermessage", "用户名有效，可以注册");
			uservalidate="ok";
		}
		request.setAttribute("userflag", uservalidate);
	%>
	<jsp:forward page="register.jsp"></jsp:forward>
</body>
</html>