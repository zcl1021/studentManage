package com.hbsi.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.User;
import com.hbsi.dao.UserDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class UserDaoImpl implements UserDao {
	Connection conn =null;
	PreparedStatement pstat=null;
	ResultSet rs = null;
	@Override
	public User lookUser(User user) {
		//获取和数据库的连接
		conn=ConnectionFactory.getConnection();
		//定义一个sql语句
		String sql="select * from user where username=? and password=? and usertypes=?";
		try {
			//创建预编译对象
			pstat=conn.prepareStatement(sql);
			//为sql语句中的3 个？赋值
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getUsertypes());
			//执行查询，返回结果集
			rs=pstat.executeQuery();
			//处理结果集
			if(rs.next()) {
				//把结果集中字段名为id的字段值取出，作为参数设置为user对象的属性id的值
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUsertypes(rs.getString("usertypes"));
				user.setVerify(rs.getString("verify"));
			}else {
				user.setUsertypes("error");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);
		}
		
		return user;
	}
	/*
	 * 把用户输入的数据添加到数据表user
	 * 1.参数user对象中只有
	 * 2. 当添加数据成功后，再次按照参数user对象己知的三个属性值为条件，查询，如果添加成功，返回记录，5个字段都会有值，
	 * 把查询得到的5个字段值封装为一个User对象，作为方法返回值返回;
	 * 把查询得到的5个字段值封装为一个User对象，作为方法返回值返回;
	 * 根据返回对象的usertypes既可以知道添加数据是否成功
	 * */
	@Override
	public User addUser(User user) {
		User u=new User();//创建User对象，初始化属性值
		conn=ConnectionFactory.getConnection();
		String sql="insert into user(username,password,usertypes,verify) values(?,?,?,?)";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			pstat.setString(3, user.getUsertypes());
			pstat.setString(4, "1");
			int i = pstat.executeUpdate();
				u=this.lookUser(user);
				
				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return u;
	}
	public boolean checkUsername(String username) {
		boolean flag=false;
		//检查用户提供的用户名在数据库中是否存在，如果存在flag=true,如果不存在，flag=false
		conn=ConnectionFactory.getConnection();//获取和数据库的连接
		String sql="select * from user where username=?";
		try{
			pstat = conn.prepareStatement(sql);//创建预编译对象
			pstat.setString(1,username);//用参数username的值设置为sql语句中第一个？的值
			rs= pstat.executeQuery();//执行查询，返回结果集对象
			if(rs.next()){//如果结果集不为空，说明用户在数据库中存在
				flag = true;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}finally{
			DBClose.close(rs,pstat,conn);
		}
		return flag;
	}
	@Override
	public boolean updatePwd(User user) {
		boolean flag=false;
		//获取和数据库的连接
		conn=ConnectionFactory.getConnection();
		try {
			//创建预编译对象
			pstat=conn.prepareStatement("update user set password=? where id=?");
		    //用参数user对象的password属性为第一个？赋值
			pstat.setString(1, user.getPassword());
			//为第二个问号赋值
			
			pstat.setInt(2, user.getId());
			//执行修改密码操作
			int i = pstat.executeUpdate();
			if(i>0) {//i>0说明受影响行数大于0
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return flag;
	}
	/*
	 * 获取总的记录数 ，已知条件是查询的表和查询的条件
	 * 所以参数中dopage对象的属性只有sql属性是有值的，
	 * 其他属性是默认值，即整数是0，字符串时null
	 */
	public int doCount(DoPage dopage) {
		//定义一个整数count代表总的记录数，初始化值为0；
		int count =0;
		//获取和数据库的连接
		conn = ConnectionFactory.getConnection();
		//定义sql语句,dopage.getsql()是查询的条件where子句
		String sql ="select count(*) from user "+dopage.getSql();
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
		String sql="select * from user "+dopage.getSql()+" limit "
				+(dopage.getNowPage()-1)*dopage.getPageSize()+","+dopage.getPageSize();
		try {
			//创建预编译对象
			pstat=conn.prepareStatement(sql);
			//执行查询返回结果集
			rs=pstat.executeQuery();
			
			//处理结果集
			while(rs.next()) {
				//定义一个user对象，属性初始化为默认
				User user = new User();
				//查询得到记录中id字段的值
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUsertypes(rs.getString("usertypes"));
				user.setVerify(rs.getString("verify"));
				//把封装好的user对象添加到列表对象中
				list.add(user);
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
	//根据id查询用户
	public User lookUserById(int id) {
		//定义用户User对象，初始化对象属性为默认值
		User user = new User();
		//获取数据库的连接
		conn = ConnectionFactory.getConnection();
		//定义sql语句
		String sql="select * from user where id="+id;
		try {
			//创建预编译对象
			pstat=conn.prepareStatement(sql);
			//执行查询，返回结果集对象
			rs=pstat.executeQuery();
				//如果结果集不为空
				if(rs.next()) {
					//用结果集id字段值设置为user对象id属性值
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setUsertypes(rs.getString("usertypes"));
					user.setVerify(rs.getString("verify"));
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstat, conn);
		}
		
		return user;//返回user对象
	}
	@Override
	public boolean deleteUser(int id) {
		//定义一个布尔变量，初始化为false
		boolean flag = false;
		//根据id查询要删除的用户对象
		User user = this.lookUserById(id);
		//获取和数据库的连接
		conn = ConnectionFactory.getConnection();
		try {
			if(user.getUsertypes().equals("admin")) {
				//如果是管理员
				//创建预编译对象，从user表中删除用户
				pstat = conn.prepareStatement("delete from user where id="+id);
				int i = pstat.executeUpdate();//执行删除操作，返回受影响的行数
				if(i>0) {	//如果受影响的行数大于0，说明删除成功
					flag=true;
				}
			}
			if(user.getUsertypes().equals("student")) {
				//如果是学生
				//创建预编译对象，从user表中删除用户
				pstat = conn.prepareStatement("delete from user where id="+id);
				int i = pstat.executeUpdate();//执行删除操作，返回受影响的行数
				if(i>0) {	//如果受影响的行数大于0，说明删除成功
					flag=true;
				}
				pstat = conn.prepareStatement("delete from student where sid="+id);//删除学生表中信息
				pstat.executeUpdate();
				pstat = conn.prepareStatement("delete from resume where sid="+id);//从简历表中删除信息
				pstat.executeUpdate();
				pstat = conn.prepareStatement("delete from recruitresume where sid="+id);//从应聘简历表中删除信息
				pstat.executeUpdate();
				pstat = conn.prepareStatement("delete from message where sid="+id);//从留言信息表中删除信息
				pstat.executeUpdate();
			}
			if(user.getUsertypes().equals("company")) {
				//如果是企业
				//创建预编译对象，从user表中删除用户
				pstat = conn.prepareStatement("delete from user where id="+id);
				int i = pstat.executeUpdate();//执行删除操作，返回受影响的行数
				if(i>0) {	//如果受影响的行数大于0，说明删除成功
					flag=true;
				}
				pstat = conn.prepareStatement("delete from company where cid="+id);//删除企业表中信息
				pstat.executeUpdate();
				pstat = conn.prepareStatement("delete from recruit where cid="+id);//从招聘表中删除信息
				pstat.executeUpdate();
				pstat = conn.prepareStatement("delete from recruitresume where cid="+id);//从应聘简历表中删除信息
				pstat.executeUpdate();
				pstat = conn.prepareStatement("delete from message where cid="+id);//从留言信息表中删除信息
				pstat.executeUpdate();
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		
		return flag;
	}

	//禁用用户
	public boolean disableUser(int id) {
		boolean flag = false;
		conn = ConnectionFactory.getConnection();
		try {
			pstat= conn.prepareStatement("update user set verify='1'where id ="+id);
		    int i =pstat.executeUpdate();//修改操作
		    if(i>0) {
		    	flag=true;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close( pstat, conn);
		}
		
		return flag;
	}
	//根据id设置用户审核未通过
	public boolean invalidUser(int id) {
		boolean flag = false;
		conn = ConnectionFactory.getConnection();
		try {
			pstat= conn.prepareStatement("update user set verify='3'where id ="+id);
		    int i =pstat.executeUpdate();//修改操作
		    if(i>0) {
		    	flag=true;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close( pstat, conn);
		}
		return flag;
	}
	//根据id设置用户审核通过
	public boolean activeUser(int id) {
		boolean flag = false;
		conn = ConnectionFactory.getConnection();
		try {
			pstat= conn.prepareStatement("update user set verify='2'where id ="+id);
		    int i =pstat.executeUpdate();//修改操作
		    if(i>0) {
		    	flag=true;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close( pstat, conn);
		}
		return flag;
	}

}
