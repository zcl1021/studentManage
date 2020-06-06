package com.hbsi.dao;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import java.util.List;

public interface StudentDao {
//定义方法，把学生信息添加到数据表Student
	boolean addStudent(Student student);
//定义方法查询学生基本信息
	Student lookStudent(int sid);
//定义方法查询所有学生信息
	List<Student> doFindAll();
//定义方法删除学生信息
	boolean deleteStudent(int sid);
//获取总记录数
	int doCount(DoPage dopage);
//获取总页数
	int doTotalPage(DoPage dopage);
//查询当前页要显示的数据
	DoPage doFindAll(DoPage dopage);
}
