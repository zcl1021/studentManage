package com.hbsi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hbsi.bean.Company;
import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.CompanyDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class CompanyDaoImpl implements CompanyDao {
	Connection conn=null;
	PreparedStatement pstat=null;
	ResultSet rs=null;
	@Override
	public boolean addCompany(Company company) {
		//定义一个布尔值，表示添加数据是否成功
		boolean flag=false;
		//连接数据库
		conn=ConnectionFactory.getConnection();
	    //定义sql语句
		String sql="insert into company values(?,?,?,?,?,?,?,?,?,?,?,?)";
	    //创建预编译对象
			try {
				pstat= conn.prepareStatement(sql);
				//赋值
				pstat.setInt(1, company.getCid());
				pstat.setString(2, company.getCompanyname());
			    pstat.setString(3, company.getUnitproperty());
				pstat.setString(4, company.getLicensenumber());
				pstat.setString(5, company.getIndustry());
				pstat.setString(6, company.getUnitscale());
				pstat.setString(7, company.getAddress());
				pstat.setString(8, company.getWebaddress());
				pstat.setString(9, company.getLinkman());
				pstat.setString(10, company.getTelephone());
				pstat.setString(11, company.getEmail());
				pstat.setString(12, company.getPostcode());
				//执行添加数据操作
				int i=pstat.executeUpdate();
					if(i>0) {//说明添加数据成功
					  flag=true;//如果添加数据成功，把布尔值设为true
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
	public int doCount(DoPage dopage) {
		//定义一个整数count代表总的记录数，初始化值为0；
		int count =0;
		//获取和数据库的连接
		conn = ConnectionFactory.getConnection();
		//定义sql语句,dopage.getsql()是查询的条件where子句
		String sql ="select count(*) from company"+dopage.getSql();
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
				String sql="select * from company "+dopage.getSql()+" limit "
						+(dopage.getNowPage()-1)*dopage.getPageSize()+","+dopage.getPageSize();
				try {
					//创建预编译对象
					pstat=conn.prepareStatement(sql);
					//执行查询返回结果集
					rs=pstat.executeQuery();
					
					//处理结果集
					while(rs.next()) {
						//定义一个company对象，属性初始化为默认
					    Company company = new Company();
						//查询得到记录中id字段的值
					    company.setCid(rs.getInt("cid"));
						company.setCompanyname(rs.getString("companyname"));
						company.setUnitproperty(rs.getString("unitproperty"));
						company.setLicensenumber(rs.getString("licensenumber"));
						company.setIndustry(rs.getString("industry"));
						
						//把封装好的company对象添加到列表对象中
						list.add(company);
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
	@Override
	public Company lookCompany(int cid) {
		       //创建和数据库的连接
				conn=ConnectionFactory.getConnection();
				//定义一个用来查询用户名、密码、用户类型是否存在的sql语句
				String sql="select * from company where cid="+cid;
				//定义一个Company对象，属性初始化为默认值
				Company company = new Company();
				//创建预编译对象
				try {
					pstat=conn.prepareStatement(sql);
					//执行查询返回结果集
					rs=pstat.executeQuery();
					//判断结果集是否为空，从结果集中提取数据
					if(rs.next()) {
					 company.setCid(rs.getInt("cid"));
				     company.setCompanyname(rs.getString("companyname"));
				     company.setUnitproperty(rs.getString("unitproperty"));
					 company.setLicensenumber(rs.getString("licensenumber"));
					 company.setIndustry(rs.getString("industry"));
					 company.setUnitscale(rs.getString("unitscale"));
					 company.setAddress(rs.getString("address"));
					 company.setWebaddress(rs.getString("webaddress"));
					 company.setLinkman(rs.getString("linkman"));
					 company.setTelephone(rs.getString("telephone"));
					 company.setEmail(rs.getString("email"));
					 company.setPostcode(rs.getString("postcode"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBClose.close(rs,pstat,conn);
				}
				return company;
	}
	//删除企业信息
	public boolean deleteCompany(int cid) {
	boolean flag = false;
	conn=ConnectionFactory.getConnection();
	//定义一个用来查询用户名、密码、用户类型是否存在的sql语句
	String sql="delete from user where id="+cid;
	try {
		pstat=conn.prepareStatement(sql);
		int i=pstat.executeUpdate();
		if(i>0) {
			flag=true;
		}
		pstat = conn.prepareStatement("delete from company where cid="+cid);//删除企业表中信息
		pstat.executeUpdate();
		pstat = conn.prepareStatement("delete from recruit where cid="+cid);//从招聘表中删除信息
		pstat.executeUpdate();
		pstat = conn.prepareStatement("delete from recruitresume where cid="+cid);//从应聘简历表中删除信息
		pstat.executeUpdate();
		pstat = conn.prepareStatement("delete from message where cid="+cid);//从留言信息表中删除信息
		pstat.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBClose.close(pstat, conn);
	}
	
	return flag;
	}
	//修改企业信息
	public boolean updateCompany(Company company) {
		boolean flag=false;
		//获取和数据库的连接
		conn=ConnectionFactory.getConnection();
		try {
			//创建预编译对象
			pstat=conn.prepareStatement("update company set companyname=?,unitproperty=?,licensenumber=?,industry=?,unitscale=?,address=?,webaddress=?,linkman=?,telephone=?,email=?,postcode=? where cid=?");
		    pstat.setString(1, company.getCompanyname());
		    pstat.setString(2, company.getUnitproperty());
		    pstat.setString(3, company.getLicensenumber());
		    pstat.setString(4, company.getUnitscale());
		    pstat.setString(5, company.getIndustry());
		    pstat.setString(6, company.getAddress());
		    pstat.setString(7, company.getWebaddress());
		    pstat.setString(8, company.getLinkman());
		    pstat.setString(9, company.getTelephone());
		    pstat.setString(10, company.getEmail());
		    pstat.setString(11, company.getPostcode());
		    pstat.setInt(12, company.getCid());
			//执行修改操作
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

}