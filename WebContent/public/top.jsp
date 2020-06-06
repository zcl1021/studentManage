<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hbsi.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top页面</title>
<style type="text/css">
.tb{
   background-image:url(/jygl/images/top-right.gif);
   backgrounf-repeat:repeat-x;
}
.td{
  font-size:12px;
  color:#ffffff;
  height:38px;
  line-height:38px;
}
</style>
<script type="text/javascript">
   function logout(){
	   var flag=window.confirm("确定退出当前账号吗");
	   if(flag){//说明点击的是按钮
		   top.location="exit.jsp";
		   
	   }
	   return false;
   }
</script>
</head>
<body>
  <table width="100%" height="64" border="0" cellspacing="0" cellpadding="0" class="tb">
   <tr>
      <td width="61%" height="60">
          <img alt="logo" src="/jygl/images/logo.gif">
      </td>
      <td width="39%" height="64" valign="top">
           <table width="100%" height="64" border="0" cellspacing="0" cellpadding="0">
             <tr>
               <td width="75%" height="38" class="td">
                 <%
                  //获取会话中封装的的登陆用户信息
                  User u=(User)session.getAttribute("user");
                 if(u.getUsertypes().equals("admin")){
                	 %>
                	 管理员
                	 <% 
                 }
                 if(u.getUsertypes().equals("student")){
                	 %>
                	 学生
                	 <%
                 }
                 if(u.getUsertypes().equals("company")){
                	 %>
                	 公司
                	 <%
                 }
                 %>
                 <%=u.getUsername() %>你好，感谢登陆使用
               </td>
               <td>
                <a href="#" target="_self" onclick="logout()">
                  <img  alt="1" src="/jygl/images/out.gif" width="46" hecight="20" border="0"/>
                </a>
               </td>
             </tr>
           </table>
      </td>
   </tr>
  </table>
</body>
</html>