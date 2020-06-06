package com.hbsi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hbsi.bean.Company;
import com.hbsi.bean.DoPage;
import com.hbsi.bean.Recruit;
import com.hbsi.dao.RecruitDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class RecruitDaoImpl implements RecruitDao {
	//定义连接数据库的接口对象
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
	//将招聘信息添加到数据表
	public boolean addRecruit(Recruit recruit) {
		boolean flag = false;
		conn=ConnectionFactory.getConnection();
		String sql="insert into recruit (cid,companyname,address,postcode,recruitment,workingplace,positiontype,edurequire,description,branch,linkman,telephone,hostpage,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			//创建预编译对象
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, recruit.getCid());
			pstat.setString(2, recruit.getCompanyname());
			pstat.setString(3, recruit.getAddress());
			pstat.setString(4, recruit.getPostcode());
			pstat.setInt(5, recruit.getRecruitment());
			pstat.setString(6, recruit.getWorkingplace());
			pstat.setString(7, recruit.getPositiontype());
			pstat.setString(8, recruit.getEdurequire());
			pstat.setString(9, recruit.getDescription());
			pstat.setString(10, recruit.getBranch());
			pstat.setString(11, recruit.getLinkman());
			pstat.setString(12, recruit.getTelephone());
			pstat.setString(13, recruit.getHostpage());
			pstat.setString(14, recruit.getEmail());
			
		
			//执行添加操作
			int i = pstat.executeUpdate();
			if(i>0) {
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
	@Override
	public int doCount(DoPage dopage) {
		//定义一个整数count代表总的记录数，初始化值为0；
		int count =0;
		//获取和数据库的连接
		conn = ConnectionFactory.getConnection();
		//定义sql语句,dopage.getsql()是查询的条件where子句
		String sql ="select count(*) from recruit"+dopage.getSql();
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
	@Override
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
	@Override
	public DoPage doFindAll(DoPage dopage) {
		//定义一个List对象，用来保存查询到的每一条记录封装成的company对象
		List list = new ArrayList();//定义列表对象初始化为空
		//获取数据库的连接
		conn=ConnectionFactory.getConnection();
		//定义sql语句
		String sql="select * from recruit "+dopage.getSql()+" limit "
				+(dopage.getNowPage()-1)*dopage.getPageSize()+","+dopage.getPageSize();
		try {
			//创建预编译对象
			pstat=conn.prepareStatement(sql);
			//执行查询返回结果集
			rs=pstat.executeQuery();
			
			//处理结果集
			while(rs.next()) {
				//定义一个recruit对象，属性初始化为默认
				Recruit recruit = new Recruit();
				//查询得到记录中id字段的值
				recruit.setRid(rs.getInt("rid"));
			    recruit.setCid(rs.getInt("cid"));
				recruit.setCompanyname(rs.getString("companyname"));
				recruit.setRecruitment(rs.getInt("recruitment"));
				recruit.setWorkingplace(rs.getString("workingplace"));
				recruit.setPositiontype(rs.getString("positiontype"));
				recruit.setEdurequire(rs.getString("edurequire"));
				
				//把封装好的company对象添加到列表对象中
				list.add(recruit);
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
	//查询招聘信息
	public Recruit lookRecruit(int rid) {
		 //创建和数据库的连接
		conn=ConnectionFactory.getConnection();
		//定义一个sql语句
		String sql="select * from recruit where rid="+rid;
		//定义一个Company对象，属性初始化为默认值
		Recruit recruit = new Recruit();
		//创建预编译对象
		try {
			pstat=conn.prepareStatement(sql);
			//执行查询返回结果集
			rs=pstat.executeQuery();
			//判断结果集是否为空，从结果集中提取数据
			if(rs.next()) {
			 recruit.setRid(rs.getInt("rid"));
			 recruit.setCid(rs.getInt("cid"));
			 recruit.setCompanyname(rs.getString("companyname"));
			 recruit.setAddress(rs.getString("address"));
			 recruit.setPostcode(rs.getString("postcode"));
			 recruit.setRecruitment(rs.getInt("recruitment"));
			 recruit.setWorkingplace(rs.getString("workingplace"));
			 recruit.setPositiontype(rs.getString("positiontype"));
			 recruit.setEdurequire(rs.getString("edurequire"));
			 recruit.setDescription(rs.getString("description"));
			 recruit.setBranch(rs.getString("branch"));
			 recruit.setLinkman(rs.getString("linkman"));
			 recruit.setTelephone(rs.getString("telephone"));
			 recruit.setHostpage(rs.getString("hostpage"));
			 recruit.setEmail(rs.getString("email"));
			 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);
		}
		return recruit;
	}
	//删除招聘信息
	public boolean deleteRecruit(int rid) {
		boolean flag=false;
		conn = ConnectionFactory.getConnection();
		String sql="delete from recruit where rid ="+rid;
		try {
			pstat=conn.prepareStatement(sql);
			int i=pstat.executeUpdate();
			if(i>0) {
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
	//修改招聘信息
	public boolean updateRecruit(Recruit recruit) {
		// TODO Auto-generated method stub
		return false;
	}


}
