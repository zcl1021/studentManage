package com.hbsi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	     //获取用户的会话对象
			HttpSession session = request.getSession();
			//从会话对象中取出封装的属性
			String username = (String)session.getAttribute("username");
			String usertype = (String)session.getAttribute("usertype");
			//取出的属性的值写到客户端
			PrintWriter out = response.getWriter();
			out.append(usertype+"账号"+username+"登陆信息错误，请重新输入");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		doGet(request, response);
	}

}
