<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册页面</title>   
    <style type="text/css">
       body{        
         background-color:#EDEDED;
       } 
       table{
         margin:30px auto;        
       }     
      .regtitle{
        font-family: 楷体_gb2312;
	    font-size: 16px;
	    line-height: 40px;
	    color: #333333;
      }
      .regtxtbt{
	    font-family:楷体,Arial;
	    font-size:16px;
	    color:#000000;
	    height:38px;
	    line-height:38px;
      }
      .regtxt{
        font-family:宋体,Arial;
	    font-size:12px;
	    color:#000000; 	
	    line-height:25px;
	    text-align:right;
      }
      #usernamemessage{
        font-size:12px;
        line-height:25px;
        color:#FF0000;	
      }
      #pwdmessage{
	    font-size:12px;
        line-height:25px;
        color:#FF0000;		
      }
    </style>	
	<script type="text/javascript">
	    function checkUser(){	    
	        var name=document.form1.username.value;//获取表单中名字为username的输入框的值
	        var flag=document.form1.uflag.value;//获取表单中名字为uflag的输入框的值
	        if((name==null)||(name.length==0)){
	        	//获取id为usermessage的元素，设置为元素内文本为"用户名不能为空"
	            document.getElementById("usernamemessage").innerHTML="用户名不能为空";
	            return false;
	        }else if(flag=="no"){	           
	             document.forms["form1"].method ="post";//设置表单form1提交方法为post
	             document.forms["form1"].username.value=name;
                 document.forms["form1"].action ="checkUser.jsp";//设置表单提交给checkUser.jsp页面
                 document.forms["form1"].submit();//提交表单
	        }else{
	           return true;
	        }
	        
	    }
	    function checkPassword(){
	        var password=document.form1.password.value;
	        if((password==null)||(password.length==0)){
	            document.getElementById("pwdmessage").innerHTML="密码不能为空";
	            return false;
	        }else{
	           return true;
	        }
	    }
	    function submitForm(){
	      return (checkUser())&&(checkPassword());
	    }	    
	</script>
  </head>  
  <body> 					             
	<form name="form1" action="/jygl/register" method="post"  onblur="submitForm()">		                
		<table width="500" class="ta" border="0"  cellpadding="0" cellspacing="0" align="center">
		    <tr height="40" valign="top">
		        <td></td>
		        <td><span class="regtitle">为学堂学生管理系统</span></td>
		    </tr>		    		                       		                       
		    <tr>		       
		       <td width="20%" height="38" align="right">
		          <span class="regtxt">用户名：</span>
		       </td>
		       <td colspan="1">	
		          <%
		             String flag="no";//定义字符串变量flag初始化为"no"
		             //从请求中获取请求属性userflag的值
		             Object uflag=request.getAttribute("userflag");
		             //如果获取到的属性值不是空(如果第一次访问register.jsp，得到而属性值是null，如果是从checkUser.jsp转发回页面，应该能得到属性值)
		             if(uflag!=null){
		                flag=String.valueOf(uflag);//吧获取到的属性值转换为字符串赋值给flag
		             }
		           %>
		          <input type="hidden" name="uflag" value="<%=flag%>">
		          <%
		             String uname="";
		             Object username=request.getAttribute("uname");
		             if(username!=null){
		                uname=String.valueOf(username);
		             }
		           %>	                                 	                               
		          <input type="text" name="username" size="20" value="<%=uname%>" onblur="checkUser()">
		          <%
		                Object msgObj=request.getAttribute("usermessage"); 
		                String msg="";
		                if(msgObj!=null){
		                   msg=String.valueOf(msgObj);
		                } 
		           %>	                        
		           <span id="usernamemessage"><%=msg%></span>		           
		       </td>
		       </tr>		                         		                    
		       <tr>
		           <td width="20%" height="38" align="right">
		             <span class="regtxt">密码：</span>
		           </td>
		           <td colspan="2">
		               <input type="password" name="password" size="20" onblur="checkPassword();">
		               <img alt="1" src="/jygl/images/luck.gif" width="19" height="18">
		               <span id="pwdmessage"></span>
		           </td>		                            
		       </tr>
		       <tr>
		           <td width="20%" height="38" align="right">
		               <span class="regtxt">用户身份：</span>
		           </td>
		           <td colspan="2">
		               <input type="radio" name="usertypes" value="student"><span class="regtxt">学生</span>&nbsp;&nbsp;&nbsp;
		               <input type="radio" name="usertypes" value="company"><span class="regtxt">企业</span>&nbsp;&nbsp;&nbsp;
		               <input type="radio" name="usertypes" value="admin"><span class="regtxt">管理员</span>&nbsp;&nbsp;&nbsp;
		           </td>
		       </tr>
		       <tr>
		          <td colspan="3">
		              <%
		                 //获取请求属性regError的值
		                 Object obj=request.getAttribute("regError");
		                if(obj != null){
		              %>
		              <font color="red" style="font-size:12px">
		                <%=String.valueOf(obj) %>
		              </font>
		              <%} %>
		          </td>
		       </tr>
		       <tr>
		           <td width="20%" height="38">&nbsp;</td>
		           <td colspan="2">		                               
		               <input type="submit" value="注册">&nbsp;&nbsp;&nbsp;
		               <input type="button" value="取消">		                                	                                
		           </td>
		       </tr>
		     </table>
		  </form>		   		   
  </body>
</html>