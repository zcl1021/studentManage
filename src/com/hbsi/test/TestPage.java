package com.hbsi.test;

import java.util.List;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.User;
import com.hbsi.dao.UserDao;
import com.hbsi.dao.impl.UserDaoImpl;

public class TestPage {
    public static void main(String[] args) {
    	//定义UserDao对象
    	UserDao ud = new UserDaoImpl();
    	//定义DoPage对象，属性值初始化为默认值
    	DoPage dopage = new DoPage();
    	//查询user表中所有记录数
    	dopage.setSql("");
    	int count = ud.doCount(dopage);
    	System.out.print(count);
    	dopage.setPageSize(2);
    	//获取总页数
    	int totalPage = ud.doTotalPage(dopage);
    	System.out.print("总页数是："+totalPage);
    	//设置当前页是第一页
    	dopage.setNowPage(1);
    	//查询第一页信息,作为参数的dopage对象有3个属性值（sql,pageSize,nowPage）；
    	dopage =ud.doFindAll(dopage);
    	System.out.println("第一句的数据是:");
        showPage(dopage.getList());
    	
    }
    public static void showPage(List<User> list) {
    	for(int i = 0;i<list.size();i++) {
    		User user =list.get(i);
    		String str ="id="+user.getId()+" username= "+user.getUsername()+" password= "+ user.getPassword()+" usertypes="+user.getUsertypes()+" verify="+user.getVerify();
    		System.out.println(str);
    	}
    }
}
