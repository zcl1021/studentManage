<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生端</title>
</head>
  <frameset rows="64,*" frameborder="no" border="0" framespacing="0">
        <frame src="/jygl/public/top.jsp" name="top" noresize="noresize" frameborder="0"
        scrolling="no" marginwidth="0" marginheight="0"/>
        <frameset cols="200,*">
          <frame src="/jygl/stu/stuleft.jsp" name="left" noresize="noresize"
          frameborder="0" scrolling=" no" marginwidth="0" marginheight="0"/>
          <frame src="/jygl/public/right.jsp" name="main" noresize="noresize"
          frameborder="0" marginwidth="0" marginheight="0" />
        
        </frameset>
  
  </frameset>
</html>