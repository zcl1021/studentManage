package com.hbsi.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.StudentDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class StudentDaoImpl implements StudentDao {
	Connection conn=null;
	PreparedStatement pstat=null;
	ResultSet rs=null;
  //重写接口方法，把参数student对象的属性值添加到数据表
	public boolean addStudent(Student student) {
	  //定义一个布尔值，表示添加数据是否成功
		boolean flag=false;
		//连接数据库
		conn=ConnectionFactory.getConnection();
		//定义sql语句
		String sql="insert into student values(?,?,?,?,?,?,?,?,?,?)";
		//创建预编译对象
		try {
			pstat= conn.prepareStatement(sql);
			//赋值
			pstat.setInt(1, student.getSid());
			pstat.setString(2, student.getSname());
			pstat.setString(3, student.getGender());
			pstat.setString(4, student.getIdnumber());
			pstat.setString(5, student.getSchool());
			pstat.setString(6, student.getDepartment());
			pstat.setString(7, student.getMajor());
			pstat.setString(8, student.getEducation());
			pstat.setString(9, student.getEntrancedate());
			pstat.setString(10, student.getNativeplace());
			//执行添加数据操作
			int i=pstat.executeUpdate();
			if(i>0) {//说明添加数据成功
				flag=true;//如果天剑数据成功，把布尔值设为true
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return flag;//返回布尔值flag值
	}
	@Override
	public Student lookStudent(int sid) {
		//创建和数据库的连接
		conn=ConnectionFactory.getConnection();
		//定义一个用来查询用户名、密码、用户类型是否存在的sql语句
		String sql="select * from student where sid="+sid;
		//定义一个Student对象，属性初始化为默认值
		Student student = new Student();
		//创建预编译对象
		try {
			pstat=conn.prepareStatement(sql);
			//执行查询返回结果集
			rs=pstat.executeQuery();
			//判断结果集是否为空，从结果集中提取数据
			if(rs.next()) {
			//用字段名作为参数取出字段sid的值，设置为student对象属性sid的值
				student.setSid(rs.getInt("sid"));
		    //用字段名取出字段sname的值
				student.setSname(rs.getString("sname"));
			//封装
				student.setGender(rs.getString("gender"));
				student.setIdnumber(rs.getString("idnumber"));
				student.setSchool(rs.getString("school"));
				student.setDepartment(rs.getString("department"));
				student.setMajor(rs.getString("major"));
				student.setEducation(rs.getString("education"));
				student.setEntrancedate(rs.getString("entrancedate"));
				student.setNativeplace(rs.getString("nativeplace"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);
		}
		return student;
	}
	//重写方法，实现所有学生信息查询
	@Override
	public List<Student> doFindAll() {
		List<Student> studentlist = new ArrayList<Student>();//定义列表对象，初始化为空的列表
		/*
		 * 通过查询数据表，把查询到的所有学生纪律，每一条记录封装为一个学生对象
		 */
		conn=ConnectionFactory.getConnection();//连接数据库
		try {
			pstat=conn.prepareStatement("select * from student");
		    rs=pstat.executeQuery();//执行查询，返回结果集
		    while(rs.next()) {//循环遍历结果集每一条记录
		    	Student student = new Student();//创建Studnet对象
		    	student.setSid(rs.getInt("sid"));
		    	student.setSname(rs.getString("sname"));
		    	student.setGender(rs.getString("gender"));
		    	student.setIdnumber(rs.getString("idnumber"));
		    	student.setSchool(rs.getString("school"));
		    	student.setDepartment(rs.getString("department"));
		    	student.setMajor(rs.getString("major"));
		    	student.setEducation(rs.getString("education"));
		    	student.setEntrancedate(rs.getString("entrancedate"));
		    	student.setNativeplace(rs.getString("nativeplace"));
		    	//把封装好的学生对象添加到列表中
		    	studentlist.add(student);
		    	
		    	
		    	
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);
		}
		return studentlist;//返回列表对象
	}
	//重写方法，删除学生信息
	@Override
	public boolean deleteStudent(int sid) {
		boolean flag=false;
		//创建和数据库的连接
		conn=ConnectionFactory.getConnection();
	    //定义一个用来查询用户名、密码、用户类型是否存在的sql语句
	  String sql1="delete from student where sid="+sid;
	  String sql2="update user set verify=3 where id="+sid;

	  try {
		pstat=conn.prepareStatement(sql1);
		int i=pstat.executeUpdate();
		if(i>0) {
		pstat=conn.prepareStatement(sql2);
		int j=pstat.executeUpdate();
		if(j>0) {
			flag=true;
		}
		}else {
			flag=false;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBClose.close(pstat,conn);
	}
		return flag;
	}
	public int doCount(DoPage dopage) {
		//定义一个整数count代表总的记录数，初始化值为0；
		int count =0;
		//获取和数据库的连接
		conn = ConnectionFactory.getConnection();
		//定义sql语句,dopage.getsql()是查询的条件where子句
		String sql ="select count(*) from student "+dopage.getSql();
		try {
			pstat=conn.prepareStatement(sql);//创建预编译对象
			rs=pstat.executeQuery();//执行查询，返回结果集
			if(rs.next()) {//如果结果集不为空
				count= rs.getInt(1);//把结果集中获取到的数据取出，赋值给变量count
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);//释放资源
		}
		return count;
	}
	/*
	 * 获取总页数，一直条件时总记录数和每页显示的记录数
	 * 即参数dopage对象的count属性和pageSize属性是已知的
	 * 其他属性可以使默认值
	 * 
	 */
	public int doTotalPage(DoPage dopage) {
		//定义变量totalPage表示总页数,初始值为0
		int totalPage=0;
		//定义变量m保存总记录数除以每页显示记录数的商
		int m = this.doCount(dopage)/dopage.getPageSize();
		//记录总记录数除以每页显示记录数的余数
		if(this.doCount(dopage)%dopage.getPageSize()>0) {
			totalPage=m+1;//总页数等于总记录数除以每页显示记录数的商加1
		}else {
			totalPage=m;
		}
		return totalPage;
	}
	/*
	 *  获取当前页需要显示的记录的集合
	 *  已知条件时:当前是第几页，每页显示的记录数，查询条件
	 *  根据参数dopage对象的nowPage,pageSize,sql属性执行查询，返回结果集，遍历
	 *  如果结果集不为空，把每一条查询到的记录封装为一个user对象，
	 *  添加到列表对象中，把列表对象设置为dopage对象的list的属性值，返回list对象
	 *  
	 */
	public DoPage doFindAll(DoPage dopage) {
		//定义一个List对象，用来保存查询到的每一条记录封装成的user对象
		List list = new ArrayList();//定义列表对象初始化为空
		//获取数据库的连接
		conn=ConnectionFactory.getConnection();
		//定义sql语句
		String sql="select * from student "+dopage.getSql()+" limit "
				+(dopage.getNowPage()-1)*dopage.getPageSize()+","+dopage.getPageSize();
		try {
			//创建预编译对象
			pstat=conn.prepareStatement(sql);
			//执行查询返回结果集
			rs=pstat.executeQuery();
			
			//处理结果集
			while(rs.next()) {
				//定义一个student对象，属性初始化为默认
				Student student = new Student();
				//查询得到记录中id字段的值et
				student.setSid(rs.getInt("sid"));
		        student.setSname(rs.getString("sname"));
		        student.setGender(rs.getString("gender"));
		        student.setSchool(rs.getString("school"));
		        student.setMajor(rs.getString("major"));
		        
				//把封装好的user对象添加到列表对象中
				list.add(student);
			}
			//把列表对象设置为dopage的属性
			dopage.setList(list);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);//释放资源
		}
		return dopage;
	}

}
