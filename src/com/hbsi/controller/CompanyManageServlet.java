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
import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.CompanyDao;
import com.hbsi.dao.impl.CompanyDaoImpl;


public class CompanyManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		  //获取请求参数action的值
			String action =request.getParameter("action");
			CompanyDao cd = new CompanyDaoImpl();
			//如果action的值是“comregister”说明来源于companyinfo.jsp页面
			if(action.equals("comregister")) {
				String id =request.getParameter("cid");
				int cid=0;
				cid=Integer.parseInt(id);
				String companyname =request.getParameter("companyname");
				String unitproperty=request.getParameter("unitproperty");
				String licensenumber = request.getParameter("licensenumber");
				String industry= request.getParameter("industry");
				String unitscale = request.getParameter("unitscale");
				String address =request.getParameter("address");
				String webaddress= request.getParameter("webaddress");
				String linkman = request.getParameter("linkman");
				String telephone=request.getParameter("telephone");
				String email= request.getParameter("email");
				String postcode = request.getParameter("postcode");
				//定义一个company对象
				Company company = new Company();//属性初始化默认
				company.setCid(cid);
			    company.setCompanyname(companyname);
			    company.setUnitproperty(unitproperty);
			    company.setLicensenumber(licensenumber);
			    company.setIndustry(industry);
			    company.setUnitscale(unitscale);
			    company.setAddress(address);
			    company.setWebaddress(webaddress);
			    company.setLinkman(linkman);
			    company.setTelephone(telephone);
			    company.setEmail(email);
			    company.setPostcode(postcode);

				//调用Dao对象添加，添加数据
				boolean flag=cd.addCompany(company);
				if(flag) {
					request.setAttribute("errorMsg", "企业用户注册成功，请联系管理员激活");
					this.gotoPage("public/login.jsp", request, response);
				}else {
					request.setAttribute("cid", company.getCid());
					this.gotoPage("company/companyinfo.jsp", request, response);
				}
			}
			//管理企业信息
			if(action.equals("companylist")) {
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
//				String sqlStr = request.getParameter("sql");
//				if(sqlStr==null) {//如果没有得到参数sql的值
//					sqlStr="";
//				}else if(sqlStr.equals("1")) {//如果得到的请求参数的值Wie1
//					sqlStr="where verify='1'";
//				}else if(sqlStr.equals("2")) {
//					sqlStr="where verify='2'";
//				}else if(sqlStr.equals("3")) {
//					sqlStr="where verify='3'";
//				}else {//如果请求参数为其他值
//					sqlStr="";
//				}
				String sqlStr ="";
				//2.设置dopage对象的sqlStr的值
				dopage.setSql(sqlStr);
				//3.设置dopage对象的pageSize的属性值
				dopage.setPageSize(10);
				//获取总的记录数
				int totalCount = cd.doCount(dopage);
				//4.设置dopage对象的count属性值
				dopage.setCount(totalCount);
				//获取总页数
				int totalPage=cd.doTotalPage(dopage);
				//5.设置dopage的totalPage属性值
				dopage.setTotalPage(totalPage);
				//6.根据前面设置，dopage对象有4个属性值，作为参数
				dopage=cd.doFindAll(dopage);
				//把6个属性封装好的dopage对象设置为请求属性
				request.setAttribute("doPage", dopage);
				this.gotoPage("admin/companyList.jsp", request, response);
			}
			if(action.equals("show")) {
				//获取会话对象
				HttpSession session = request.getSession();
				//从会话对象中取出登陆用户对象
				User user=(User)session.getAttribute("user");
				//如登陆用户是企业，获取用户id即为企业的cid
				int cid=0;
				if(user.getUsertypes().equals("company")) {
					cid=user.getId();
				}else {//如果登陆用户不是企业
					//获取企业id的值
					cid=Integer.parseInt(request.getParameter("cid"));
					
				}
				//根据企业的cid查询企业的基本信息
				Company company = cd.lookCompany(cid);
				//把查询记录装好的company对象设置为请求属性company值
				request.setAttribute("company", company);
				//请求转发到company/showCompany.jsp页面
				this.gotoPage("company/showCompany.jsp",request,response);
			}
			if(action.equals("delete")) {
				int cid =0;
				String cidStr=request.getParameter("cid");
				try {
					cid=Integer.parseInt(cidStr);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cd.deleteCompany(cid);
				this.gotoPage("companyManage?action=companylist", request, response);
			}
			if(action.equals("update")) {
				//获取会话对象
				HttpSession session = request.getSession();
				//从会话对象中取出登陆用户对象
				User user=(User)session.getAttribute("user");
				//如登陆用户是企业，获取用户id即为企业的cid
				int cid=0;
				if(user.getUsertypes().equals("company")) {
					cid=user.getId();
				}
				//根据企业的cid查询企业的基本信息
				Company company = cd.lookCompany(cid);
				//把查询记录装好的company对象设置为请求属性company值
				request.setAttribute("company", company);
				//请求转发到company/showCompany.jsp页面
				this.gotoPage("company/updateCompany.jsp",request,response);
			}
			if(action.equals("updateCompany")) {
				//获取请求参数cid的值
				int cid=0;
				//把cid参数值转换为整数赋值给cid
				try {
					 cid=Integer.parseInt(request.getParameter("cid"));
					System.out.print(cid);
					
				}catch(NumberFormatException e) {
					e.printStackTrace();
				}
				String companyname = request.getParameter("companyname");
				String unitproperty = request.getParameter("unitproperty");
				String licensenumber = request.getParameter("licensenumber");
				String industry = request.getParameter("industry");
				String unitscale = request.getParameter("unitscale");
				String address = request.getParameter("address");
				String webaddress = request.getParameter("webaddress");
				String linkman = request.getParameter("linkman");
				String telephone = request.getParameter("telephone");
				String email = request.getParameter("email");
				String postcode = request.getParameter("postcode");
				Company company  = new Company();
				company.setCid(cid);
				company.setCompanyname(companyname);
				company.setUnitproperty(unitproperty);
				company.setLicensenumber(licensenumber);
				company.setIndustry(industry);
				company.setUnitscale(unitscale);
				company.setAddress(address);
				company.setWebaddress(webaddress);
				company.setLinkman(linkman);
				company.setTelephone(telephone);
				company.setEmail(email);
				company.setPostcode(postcode);
				boolean flag = cd.updateCompany(company);
				if(flag) {
					request.setAttribute("company", company);
					this.gotoPage("/company/updateCompany.jsp",request,response);
				}else {
					this.gotoPage("/public/error.jsp",request,response);
				}
			}
			if(action.equals("create")) {
				//获取会话对象
				HttpSession session = request.getSession();
				//从会话对象中取出登陆用户对象
				User user=(User)session.getAttribute("user");
				//如登陆用户是企业，获取用户id即为企业的cid
				int cid=0;
				if(user.getUsertypes().equals("company")) {
					cid=user.getId();
				}else {//如果登陆用户不是企业
					//获取企业id的值
					cid=Integer.parseInt(request.getParameter("cid"));
				}
				//根据企业的cid查询企业的基本信息
				Company company = cd.lookCompany(cid);
				//把查询记录装好的company对象设置为请求属性company值
				request.setAttribute("company", company);
				//请求转发到company/showCompany.jsp页面
				this.gotoPage("public/createRecruit.jsp",request,response);
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
