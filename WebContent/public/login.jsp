<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>为学堂学生就业信息管理系统</title>
<link type="text/css" rel="stylesheet" href="/jygl/css/skin.css">
<script type="text/javascript">
   function refresh(){
	   //点击是应该再次请求，重新画一张图片
	   var imgEle=document.getElementById("codeimage");//获取id获取图片标签对象
	   imgEle.src="/jygl/createImage?"+new Date();//使用new Date（）实现路径的刷新，预防缓存中图片重新加载
	   
   }
</script>
</head>
<body>
   <table width="350" border="0" align="center" cellspacing="0" cellpadding="0">
      <tr><td height="150">&nbsp;</td></tr>
      <tr>
        <td height="64" colspan="2" align="center">        
           <span class="logintitle">为学堂学生就业信息管理系统</span>      
    <!-- action="/jygl/login"中的第一个“/”代表http://服务器名或ip:端口号，
    action="/jygl/login"的完整路径是http://localhost:8080/jygl/login
     -->
    <form action="/jygl/checkCode" method="post" name="form1">
       <table width="100%" height="143" border="0" cellspacing="0" cellpadding="0">
          <tr>
             <td width="25%" height="38" align="right">                             
                <span class="logintxt">用户名：&nbsp;&nbsp;</span>
             </td>
             <td height="38" colspan="2">
                <input name="username" type="text" size="20">
             </td>
          </tr>
          <tr>
             <td height="35" align="right">
               <span class="logintxt">密    码：&nbsp;&nbsp;</span>
             </td>
             <td height="35" colspan="2">
                <input name="password" type="password" size="20">
                <img alt="" src="/jygl/images/luck.gif" width="19" height="18">
             </td>
          </tr>
          <tr>
             <td height="35" align="right">
                 <span class="logintxt">用户身份：</span>
             </td>
             <td height="35" colspan="2">
                <input type="radio" name="usertypes" value="student" checked />
                <span class="logintxt">学生</span>&nbsp;
                <input type="radio" name="usertypes" value="company" />
                <span class="logintxt">企业</span>&nbsp;
                <input type="radio" name="usertypes" value="admin" />
                <span class="logintxt">管理员</span>&nbsp;
             </td>
          </tr>
          <%--添加一行，使用内置对象request取出的封装的属性errorMsg的值 --%>
            <tr>
               <td colspan="3">
                   <%
                      //获取请求中封装的属性
                      Object obj=request.getAttribute("errorMsg");
                      if(obj !=null){//如果得到了属性值
                   %>
                   <font color="red" style="font-size:12px">
                     <%=String.valueOf(obj) %>
                   </font>
                   <%
                      }
                   %>
               </td>
            </tr>
            <!-- 实现验证码技术，显示 验证码 -->
            <tr>
               <td><span class="logintxt">验证码</span></td>
               <td colspan="2">
                  <input type="text" name="code" size="6">
                  <!-- src的值是一个Servlet的url -->
                  <img alt="*" src="/jygl/createImage" onclick="refresh()" id="codeimage" title="单击跟换验证码">
                  <span class="errorcode">${codeMsg}</span>
               </td>          
            </tr>
          <tr>
            <td height="35"></td>
            <td width="20%" height="35"></td>
            <td>
              <a href="/jygl/public/register.jsp">新用户注册</a>
            </td>
          </tr>
          <tr>
             <td height="35">&nbsp;&nbsp;</td>
             <td height="35">
                <input type="submit" value="登录" class="btn" />
                <input type="reset" value="取消" class="btn" />
             </td>
          </tr>
       </table>
      </td>
     </tr>
   </form>
  </table>
</body>
</html>