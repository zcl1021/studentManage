package com.hbsi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hbsi.bean.Company;
import com.hbsi.bean.DoPage;
import com.hbsi.bean.Recruit;
import com.hbsi.bean.User;
import com.hbsi.dao.RecruitDao;
import com.hbsi.dao.impl.RecruitDaoImpl;



public class RecruitManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 //获取请求参数action的值
		String action =request.getParameter("action");
		RecruitDao rd = new RecruitDaoImpl();
		if(action.equals("createRecruit")) {
			int cid=0;
		    cid=Integer.parseInt(request.getParameter("cid"));
			
			String companyname = request.getParameter("companyname");
			String address = request.getParameter("address");
			String postcode = request.getParameter("postcode");
			int recruitment =Integer.parseInt(request.getParameter("recruitment"));
			String workingplace = request.getParameter("workingplace");
			String positiontype = request.getParameter("positiontype");
			String edurequire = request.getParameter("edurequire");
			String description = request.getParameter("description");
			String branch = request.getParameter("branch");
			String linkman = request.getParameter("linkman");
			String telephone = request.getParameter("telephone");
			String hostpage = request.getParameter("hostpage");
			String email = request.getParameter("email");
			Recruit recruit = new Recruit();
			recruit.setCid(cid);
			recruit.setCompanyname(companyname);
			recruit.setAddress(address);
			recruit.setPostcode(postcode);
			recruit.setRecruitment(recruitment);
			recruit.setWorkingplace(workingplace);
			recruit.setPositiontype(positiontype);
			recruit.setEdurequire(edurequire);
			recruit.setDescription(description);
			recruit.setBranch(branch);
			recruit.setLinkman(linkman);
			recruit.setTelephone(telephone);
			recruit.setHostpage(hostpage);
			recruit.setEmail(email);
			boolean flag = rd.addRecruit(recruit);
			if(flag) {
				HttpSession session = request.getSession();
				//从对话中取出原来登陆用户对象
				User loginUser=(User)session.getAttribute("user");
				if(loginUser.getUsertypes().equals("admin")) {
				this.gotoPage("/recruitManage?action=recruitlist",request,response);
				}else {
					this.gotoPage("/recruitManage?action=edit",request,response);
				}
				
			}else {
				this.gotoPage("/public/error.jsp",request,response);
			}
			
			
		}
		//管理企业招聘信息
		if(action.equals("recruitlist")) {
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
			//String sqlStr = request.getParameter("sql");
			String sqlStr ="";
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
			//2.设置dopage对象的sqlStr的值
			dopage.setSql(sqlStr);
			//3.设置dopage对象的pageSize的属性值
			dopage.setPageSize(10);
			//获取总的记录数
			int totalCount = rd.doCount(dopage);
			//4.设置dopage对象的count属性值
			dopage.setCount(totalCount);
			//获取总页数
			int totalPage=rd.doTotalPage(dopage);
			//5.设置dopage的totalPage属性值
			dopage.setTotalPage(totalPage);
			//6.根据前面设置，dopage对象有4个属性值，作为参数
			dopage=rd.doFindAll(dopage);
			//把6个属性封装好的dopage对象设置为请求属性
			request.setAttribute("doPage", dopage);
			this.gotoPage("admin/recruitList.jsp", request, response);
		
		
		}
		if(action.equals("show")) {
			//获取会话对象
			HttpSession session = request.getSession();
			//从会话对象中取出登陆用户对象
			User user=(User)session.getAttribute("user");
			//如登陆用户是企业，获取用户id即为企业的cid
//			int cid=0;
//			if(user.getUsertypes().equals("company")) {
//				cid=user.getId();
//			}else {//如果登陆用户不是企业
				//获取企业id的值
				int rid=Integer.parseInt(request.getParameter("rid"));
				
//			}
			//根据企业的cid查询企业的基本信息
			Recruit recruit = rd.lookRecruit(rid);
			//把查询记录装好的company对象设置为请求属性company值
			request.setAttribute("recruit", recruit);
			//请求转发到页面
			this.gotoPage("public/showRecruit.jsp",request,response);
		}
		if(action.equals("edit")) {
			//获取会话对象
			HttpSession session = request.getSession();
			//从会话对象中取出登陆用户对象
			User user=(User)session.getAttribute("user");
			int cid = user.getId();
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
			//String sqlStr = request.getParameter("sql");
			//if(sqlStr==null) {//如果没有得到参数sql的值
				String sqlStr=" where cid = "+cid;
			//}
			//2.设置dopage对象的sqlStr的值
			dopage.setSql(sqlStr);
			//3.设置dopage对象的pageSize的属性值
			dopage.setPageSize(10);
			//获取总的记录数
			int totalCount = rd.doCount(dopage);
			//4.设置dopage对象的count属性值
			dopage.setCount(totalCount);
			//获取总页数
			int totalPage=rd.doTotalPage(dopage);
			//5.设置dopage的totalPage属性值
			dopage.setTotalPage(totalPage);
			//6.根据前面设置，dopage对象有4个属性值，作为参数
			dopage=rd.doFindAll(dopage);
			//把6个属性封装好的dopage对象设置为请求属性
			request.setAttribute("doPage", dopage);
			this.gotoPage("company/CompanyRecruitList.jsp", request, response);
		}
		if(action.equals("delete")) {
			int rid=0;
			String ridStr=request.getParameter("rid");
			try {
				rid=Integer.parseInt(ridStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean flag =rd.deleteRecruit(rid);
			if(flag) {
				HttpSession session = request.getSession();
				//从对话中取出原来登陆用户对象
				User loginUser=(User)session.getAttribute("user");
				if(loginUser.getUsertypes().equals("admin")) {
				this.gotoPage("/recruitManage?action=recruitlist",request,response);
				}else if(loginUser.getUsertypes().equals("company")) {
					this.gotoPage("/recruitManage?action=edit",request,response);
				}else {
					this.gotoPage("/public/success.jsp",request,response);
				}
				
			}else {
				this.gotoPage("/public/error.jsp",request,response);
			}
		}
		if(action.equals("recuit")) {
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
			//String sqlStr = request.getParameter("sql");
			String sqlStr ="";
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
			//2.设置dopage对象的sqlStr的值
			dopage.setSql(sqlStr);
			//3.设置dopage对象的pageSize的属性值
			dopage.setPageSize(10);
			//获取总的记录数
			int totalCount = rd.doCount(dopage);
			//4.设置dopage对象的count属性值
			dopage.setCount(totalCount);
			//获取总页数
			int totalPage=rd.doTotalPage(dopage);
			//5.设置dopage的totalPage属性值
			dopage.setTotalPage(totalPage);
			//6.根据前面设置，dopage对象有4个属性值，作为参数
			dopage=rd.doFindAll(dopage);
			//把6个属性封装好的dopage对象设置为请求属性
			request.setAttribute("doPage", dopage);
			this.gotoPage("stu/stuRecruitList.jsp", request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	 //定义私有方法，实现请求转发
	private void gotoPage(String url,HttpServletRequest request,HttpServletResponse response)
	  throws ServletException,IOException{
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
	}

}
