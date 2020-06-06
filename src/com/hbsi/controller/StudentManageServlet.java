package com.hbsi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.StudentDao;
import com.hbsi.dao.impl.StudentDaoImpl;



public class StudentManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	  //获取请求参数action的值
		String action =request.getParameter("action");
		StudentDao sd=new StudentDaoImpl();
		if(action.equals("studentlist")) {
			//List<Student> students= sd.doFindAll();
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
//			String sqlStr = request.getParameter("sql");
//			if(sqlStr==null) {//如果没有得到参数sql的值
//				sqlStr="";
//			}else if(sqlStr.equals("1")) {//如果得到的请求参数的值Wie1
//				sqlStr="where verify='1'";
//			}else if(sqlStr.equals("2")) {
//				sqlStr="where verify='2'";
//			}else if(sqlStr.equals("3")) {
//				sqlStr="where verify='3'";
//			}else {//如果请求参数为其他值
//				sqlStr="";
//			}
			String sqlStr ="";
			//2.设置dopage对象的sqlStr的值
			dopage.setSql(sqlStr);
			//3.设置dopage对象的pageSize的属性值
			dopage.setPageSize(10);
			//获取总的记录数
			int totalCount = sd.doCount(dopage);
			//4.设置dopage对象的count属性值
			dopage.setCount(totalCount);
			//获取总页数
			int totalPage=sd.doTotalPage(dopage);
			//5.设置dopage的totalPage属性值
			dopage.setTotalPage(totalPage);
			//6.根据前面设置，dopage对象有4个属性值，作为参数
			dopage=sd.doFindAll(dopage);
			//把6个属性封装好的dopage对象设置为请求属性
			request.setAttribute("doPage", dopage);
			//request.setAttribute("list", students);
			this.gotoPage("admin/studentList.jsp", request, response);
		}
		if(action.equals("show")) {//说明用户点击了左导航连接查看个人基本信息
			//获取会话对象
			HttpSession session = request.getSession();
			//从会话对象中取出登陆用户对象
			User user=(User)session.getAttribute("user");
			//如果登陆用户是学生，获取用户id即为学生的sid
			int sid=0;
			if(user.getUsertypes().equals("student")) {
				sid=user.getId();
			}else {//如果登陆用户不是学生
				//获取学生id的值
				sid=Integer.parseInt(request.getParameter("sid"));
				
			}
			//根据学生的sid查询学生的基本信息
			Student student =sd.lookStudent(sid);
			//把查询记录装好的student对象设置为请求属性student值
			request.setAttribute("studnet", student);
			//请求转发到stu/showStudent.jsp页面
			this.gotoPage("stu/showStudent.jsp",request,response);
		}
		//如果action的值是“sturegister”说明来源于studentinfo.jsp页面
		if(action.equals("sturegister")) {
			String id =request.getParameter("sid");
			int sid=0;
			sid=Integer.parseInt(id);
			String sname=request.getParameter("sname");
			String gender = request.getParameter("gender");
			String idnumber= request.getParameter("idnumber");
			String school = request.getParameter("school");
			String department =request.getParameter("department");
			String major= request.getParameter("major");
			String education = request.getParameter("education");
			String entrancedate=request.getParameter("entrancedate");
			String nativeplace = request.getParameter("nativeplace");
			//定义一个student对象
			Student student = new Student();//属性初始化默认值
			//使用用户表单提交的sid值设置为student对象sid属性值
			student.setSid(sid);
			//使用用户表单提交的sname的值设置为student对象sname属性值
			student.setSname(sname);
			student.setGender(gender);
			student.setIdnumber(idnumber);
			student.setSchool(school);
			student.setDepartment(department);
			student.setMajor(major);
			student.setEducation(education);
			student.setEntrancedate(entrancedate);
			student.setNativeplace(nativeplace);
			//调用Dao对象添加，添加数据
			boolean flag=sd.addStudent(student);
			if(flag) {
				request.setAttribute("errorMsg", "学生用户注册成功，请联系管理员激活");
				this.gotoPage("public/login.jsp", request, response);
			}else {
				request.setAttribute("sid", student.getSid());
				this.gotoPage("stu/studentinfo.jsp", request, response);
			}
		}   
		    //删除学生信息
		    if(action.equals("delete")) {
			
			int sid =0;
			String sidStr=request.getParameter("sid");
			try {
				sid=Integer.parseInt(sidStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sd.deleteStudent(sid);
			this.gotoPage("studentManage?action=studentlist", request, response);
		}
		//根据学生的sid删除学生的基本信息
		//Boolean flag =sd.deleteStudent(sid);
		//.gotoPage("stu/studentinfo.jsp", request, response);
	
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
