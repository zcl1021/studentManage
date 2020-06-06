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
import com.hbsi.bean.Resume;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.ResumeDao;
import com.hbsi.dao.StudentDao;
import com.hbsi.dao.impl.ResumeDaoImpl;
import com.hbsi.dao.impl.StudentDaoImpl;


@WebServlet("/ResumeManageServlet")
public class ResumeManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		  //获取请求参数action的值
		  String action =request.getParameter("action");
		  ResumeDao rd = new ResumeDaoImpl();
		  StudentDao sd=new StudentDaoImpl();
		  if(action.equals("create")) {
			  HttpSession session = request.getSession();
				//从会话对象中取出登陆用户对象
				User user=(User)session.getAttribute("user");
				//如登陆用户是企业，获取用户id即为企业的cid
				int sid=user.getId();
				Student student = sd.lookStudent(sid);
				request.setAttribute("student", student);
				this.gotoPage("resume/resume.jsp", request, response);
		  }
		  if(action.equals("add")) {
				String id =request.getParameter("sid");
				int sid=0;
				sid=Integer.parseInt(id);
				String sname=request.getParameter("sname");
				String gender = request.getParameter("gender");
				String birthdate = request.getParameter("birthdate");
				String nation = request.getParameter("nation");
				String politics = request.getParameter("politics");
				String graduation = request.getParameter("graduation");
				String school = request.getParameter("school");
				String major = request.getParameter("major");
				String education = request.getParameter("education");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String foreignlanguage = request.getParameter("foreignlanguage");
				String hobby = request.getParameter("hobby");
				String practice = request.getParameter("practice");
				String position = request.getParameter("position");
				String honor = request.getParameter("honor");
				String research = request.getParameter("research");
				String selfevaluation = request.getParameter("selfevaluation");
				Resume resume =new Resume();
				resume.setSid(sid);
				resume.setSname(sname);
				resume.setGender(gender);
				resume.setBirthdate(birthdate);
				resume.setNation(nation);
				resume.setPolitics(politics);
				resume.setGraduation(graduation);
				resume.setSchool(school);
				resume.setMajor(major);
				resume.setEducation(education);
				resume.setEmail(email);
				resume.setPhone(phone);
				resume.setForeignlanguage(foreignlanguage);
				resume.setHobby(hobby);
				resume.setPractice(practice);
				resume.setPosition(position);
				resume.setHonor(honor);
				resume.setResearch(research);
				resume.setSelfevaluation(selfevaluation);
				boolean flag = rd.addResume(resume);
				if(flag) {
					this.gotoPage("public/success.jsp", request, response);
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
