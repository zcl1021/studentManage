package com.hbsi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hbsi.bean.User;
import com.hbsi.dao.UserDao;
import com.hbsi.dao.impl.UserDaoImpl;


@WebServlet("/LoginServet")
public class LoginServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LoginServet() {
        super();
   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取客户端提交的表单数据
		String name =request.getParameter("username");
		String passwd =request.getParameter("password");
		String usertype =request.getParameter("usertypes");
		//获取用户的会话对象
		HttpSession session = request.getSession();
		//把从请求中获取的用户名、密码、用户身份信息封装为User对象属性值
		User user = new User();
		user.setUsername(name);
		user.setPassword(passwd);
		user.setUsertypes(usertype);
		//用UserDao实现类创建UserDao对象
		UserDao ud = new UserDaoImpl();
		//调用接口对象的lookUser方法验证用户输入值是否在user表中
		User u = ud.lookUser(user);
		//如果返回的对象u的usertypes属性值是error，说明表中没有用户输入的信息
		if(u.getUsertypes().equals("error")) {
			request.setAttribute("errorMsg","用户名或密码错误，请重新输入");
			//然后请求转发会登陆页面
			this.gotoPage("public/login.jsp",request,response);
			
		}else {
			if(u.getVerify().equals("1")) {
				request.setAttribute("errorMag","用户未激活，请联系管理员激活");
				//然后请求转发会登陆页面
				this.gotoPage("public/login.jsp",request,response);
			}
			if(u.getVerify().equals("2")) {
				//把用户对象设置为会话属性值
				session.setAttribute("user", u);
				if(u.getUsertypes().equals("admin")) {
					//如果登陆用户是管理员，进入管理员主页面
					this.gotoPage("admin/index.jsp", request, response);
				
				}
				if(u.getUsertypes().equals("student")) {
					//如果登陆用户是学生，进入管理员主页面
					this.gotoPage("stu/stuIndex.jsp", request, response);
				}
				if(u.getUsertypes().equals("company")) {
					//如果登陆用户企业，进入管理员主页面
					this.gotoPage("company/companyIndex.jsp", request, response);
				}
			}
            if(u.getVerify().equals("3")) {
            	request.setAttribute("errorMsg","用户审核未通过，请重新注册，如实填写信息");
    			//然后请求转发会登陆页面
    			this.gotoPage("public/login.jsp",request,response);
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
