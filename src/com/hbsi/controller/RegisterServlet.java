package com.hbsi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbsi.bean.User;
import com.hbsi.dao.UserDao;
import com.hbsi.dao.impl.UserDaoImpl;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String usertypes = request.getParameter("usertypes");
		//定义User对象,属性初始化为默认值
		User user =new User();
		//用获取到的请求值设置为user对象的属性值
		user.setUsername(username);
        user.setPassword(password);
        user.setUsertypes(usertypes);
        //创建UserDao对象
        UserDao ud = new UserDaoImpl();
        //调用添加用户的方法
        User u = ud.addUser(user);
        if(u.getUsertypes().equals("error")) {//说明没有添加用户成功
        	//设置一个请求属性，提示添加用户没有成功
        	request.setAttribute("regError", "用户注册未成功，请重新注册");
        	//返回注册页面
        	this.gotoPage("public/register.jsp", request, response);
        }else {//添加用户成功
        	if(u.getUsertypes().equals("admin")) {//如果注册用户是管理员
        		//在请求中封装信息
        		request.setAttribute("errorMsg", "管理员用户注册成功，请联系管理员激活用户");
        		//请求转发到login.jsp页面
        		this.gotoPage("public/login.jsp", request, response);
        	}
        	if(u.getUsertypes().equals("student")) {
        		//把用户id封装为请求属性的值
        		request.setAttribute("sid", u.getId());
        		request.setAttribute("errorMsg", "学生用户注册成功，请联系管理员激活用户");
        		//请求转发到stu/studentInfo.jsp页面
        		this.gotoPage("stu/studentinfo.jsp", request, response);
        	}
        	if(u.getUsertypes().equals("company")) {
        		request.setAttribute("cid", u.getId());
        		//请求转发到companyinfo.jsp
        		request.setAttribute("errorMsg", "企业用户注册成功，请联系管理员激活用户");
        		this.gotoPage("company/companyinfo.jsp", request, response);
        	}
        }
	}
	  //定义私有方法，实现请求转发
		private void gotoPage(String url,HttpServletRequest request,HttpServletResponse response)
		  throws ServletException,IOException{
			RequestDispatcher dispatcher=request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}

}
