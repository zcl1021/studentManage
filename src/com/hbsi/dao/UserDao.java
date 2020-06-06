package com.hbsi.dao;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.User;

public interface UserDao {
   //定义方法，查询用户表中（user表）是否有特定信息
	User lookUser(User user);
	//定义方法，把数据添加到user表
	User addUser(User user);
	//检查用户名是否存在
	boolean checkUsername(String username);
	//修改密码
	boolean updatePwd(User user);
	//获取总记录数
	int doCount(DoPage dopage);
	//获取总页数
	int doTotalPage(DoPage dopage);
	//查询当前页要显示的数据
	DoPage doFindAll(DoPage dopage);
	//定义方法根据id查询用户
	User lookUserById(int id);
	//删除用户
	boolean deleteUser(int id);
	//禁用用户
	boolean disableUser(int id);
	//设置用户审核未通过的
	boolean invalidUser(int id);
	//设置用户审核通过
	boolean activeUser(int id);
}
