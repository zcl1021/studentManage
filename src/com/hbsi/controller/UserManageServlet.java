package com.hbsi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.User;
import com.hbsi.dao.UserDao;
import com.hbsi.dao.impl.UserDaoImpl;



public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	      //创建UserDao对象
		UserDao ud = new UserDaoImpl();
		String action = request.getParameter("action");
		if(action.equals("disable")) {
			int id=0;//定义变量id初始值为0
			//获取请求参数中id的值
			String idStr= request.getParameter("id");
			try {
				id=Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//以id为实参，调用方法禁用用户
			ud.disableUser(id);
			this.gotoPage("userManage?action=list", request, response);
		}
		//删除用户
		if(action.equals("delete")) {
			int id=0;//定义变量id初始值为0
			//获取请求参数中id的值
			String idStr= request.getParameter("id");
			try {
				id=Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//以id为实参，调用方法删除用户
			ud.deleteUser(id);
			this.gotoPage("userManage?action=list", request, response);
		}
		//审核未通过
		if(action.equals("invalid")) {
			int id =0;
			String idStr=request.getParameter("id");
			try {
				id=Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ud.invalidUser(id);
			this.gotoPage("userManage?action=list", request, response);
		}
		//审核通过
		if(action.equals("active")) {
			int id =0;
			String idStr=request.getParameter("id");
			try {
				id=Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ud.activeUser(id);
			this.gotoPage("userManage?action=list", request, response);
		}
		if(action.equals("list")) {//说明是管理用户请求
			//创建Dopage对象，初始化对象的属性为默认值
			DoPage dopage = new DoPage();
			//获取当前页面参数（获取请求中的当前页码）
			String pageNum=request.getParameter("page");
			int pageNo=0;//定义变量pageNo表示当前是第几页
			if(pageNum==null) {//如果没有从请求汇总获取到page参数的值
				pageNo=1;
			}else {//如果从请求中获取到了参数page的值
				pageNo=Integer.parseInt(pageNum);//把得到的参数值转化为整数复制给pageNum
				
			}
			//1.设置dopage对象的nowpage属性值为pageNo
			dopage.setNowPage(pageNo);
			//从请求中获取请求参数sql的值
			String sqlStr = request.getParameter("sql");
			if(sqlStr==null) {//如果没有得到参数sql的值
				sqlStr="";
			}else if(sqlStr.equals("1")) {//如果得到的请求参数的值Wie1
				sqlStr=" where verify= 1";
			}else if(sqlStr.equals("2")) {
				sqlStr=" where verify= 2";
			}else if(sqlStr.equals("3")) {
				sqlStr=" where verify= 3";
			}else {//如果请求参数为其他值
				sqlStr="";
			}
			//2.设置dopage对象的sqlStr的值
			dopage.setSql(sqlStr);
			//3.设置dopage对象的pageSize的属性值
			dopage.setPageSize(10);
			//获取总的记录数
			int totalCount = ud.doCount(dopage);
			//4.设置dopage对象的count属性值
			dopage.setCount(totalCount);
			//获取总页数
			int totalPage=ud.doTotalPage(dopage);
			//5.设置dopage的totalPage属性值
			dopage.setTotalPage(totalPage);
			//6.根据前面设置，dopage对象有5个属性值，作为参数
			dopage=ud.doFindAll(dopage);
			//把6个属性封装好的dopage对象设置为请求属性
			request.setAttribute("doPage", dopage);
			this.gotoPage("admin/listUser.jsp", request, response);
		}
		if(action.equals("update")) {//说明是修改密码的请求
			//获取请求参数id的值
			int id=0;
			//把id参数值转换为整数赋值给id
			try {
				id=Integer.parseInt(request.getParameter("id"));
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			//获取用户输入的新密码
			String pwd = request.getParameter("password");
			//用默认构造方法创建user对象，属性初始化默认值
			User user = new User();
			//用获取的参数id的值设置为user对象的id属性值
			user.setId(id);
			user.setPassword(pwd);
			boolean flag=ud.updatePwd(user);
			if(flag) {//说明修改成功
				//获取会话对象
				HttpSession session = request.getSession();
				//从对话中取出原来登陆用户对象
				User loginUser=(User)session.getAttribute("user");
				if(loginUser.getUsertypes().equals("admin")) {
					this.gotoPage("userManage?action=list", request, response);
				}else {//如果不是管理员
					loginUser.setPassword(pwd);
					//重新设置会话中的登陆用户
					session.setAttribute("user", loginUser);
					this.gotoPage("public/success.jsp", request, response);
				}
				
			}else {
				this.gotoPage("public/error.jsp", request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	 //定义私有方法，实现请求转发
		private void gotoPage(String url,HttpServletRequest request,HttpServletResponse response)
		  throws ServletException,IOException{
			RequestDispatcher dispatcher=request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}

}
