package com.hbsi.dao;

import com.hbsi.bean.Company;
import com.hbsi.bean.DoPage;
import com.hbsi.bean.Recruit;

public interface RecruitDao {
	//定义方法，把招聘添加到数据表recruit
		boolean addRecruit(Recruit recruit);
	//获取总记录数
		int doCount(DoPage dopage);
	//获取总页数
		int doTotalPage(DoPage dopage);
	//查询当前页要显示的数据
		DoPage doFindAll(DoPage dopage);
	//定义方法查询招聘信息
		Recruit lookRecruit(int rid);
	//定义方法删除招聘信息
		boolean deleteRecruit(int rid);
	//定义方法修改招聘信息
		boolean updateRecruit(Recruit recruit);
}
