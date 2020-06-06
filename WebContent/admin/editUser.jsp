<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户修改密码</title>
<script type="text/javascript">
    function check(){
    	//获取密码输入框的值
    	var password=document.getElementById("pwd").value;
    	if(password == ""||password.length==0){
    		alert("密码不能为空")
    		return false;
    	}else{
    		return true;
    	}
    }
</script>
</head>
<body>
   <form action="/jygl/userManage" method="post" onsubmit="check();">
   <input type="hidden" name="action" value="update"/>
   <input type="hidden" name="id" value="${param.id }">
   <table border="0" width="350" width="150">
        <tr>
           <td align="right">新密码:</td>
           <td>
               <input type="password" name="password" id="pwd"/>
           </td>
        </tr>   
        <tr>
           <td colspan="2" align="center">
               <input type="submit" value="提交">&nbsp;&nbsp;
               <input type="reset" value="取消">
           </td>
        </tr>
   </table>
   
   
   </form>
</body>
</html>