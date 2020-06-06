package com.hbsi.dao;

import com.hbsi.bean.Company;
import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;

public interface CompanyDao {
	//定义方法，把企业信息添加到数据表company
		boolean addCompany(Company company);
	//获取总记录数
		int doCount(DoPage dopage);
	//获取总页数
		int doTotalPage(DoPage dopage);
	//查询当前页要显示的数据
		DoPage doFindAll(DoPage dopage);
	//定义方法查询企业基本信息
		Company lookCompany(int cid);
	//定义方法删除企业信息
		boolean deleteCompany(int cid);
	//定义方法修改企业信息
		boolean updateCompany(Company company);
}
