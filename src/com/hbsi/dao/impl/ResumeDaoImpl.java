package com.hbsi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbsi.bean.Resume;
import com.hbsi.dao.ResumeDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class ResumeDaoImpl implements ResumeDao {

	@Override
	public boolean addResume(Resume resume) {
		Connection conn=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		boolean flag = false;
		//连接数据库
		conn=ConnectionFactory.getConnection();
	    //定义sql语句
		String sql="insert into resume values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    //创建预编译对象
			try {
				pstat= conn.prepareStatement(sql);
				//赋值
				pstat.setInt(1, resume.getSid());
				pstat.setString(2, resume.getSname());
			    pstat.setString(3, resume.getGender());
				pstat.setString(4, resume.getBirthdate());
				pstat.setString(5, resume.getNation());
				pstat.setString(6, resume.getPolitics());
				pstat.setString(7, resume.getGraduation());
				pstat.setString(8, resume.getSchool());
				pstat.setString(9, resume.getMajor());
				pstat.setString(10, resume.getEducation());
				pstat.setString(11, resume.getEmail());
				pstat.setString(12, resume.getPhone());
				pstat.setString(13, resume.getForeignlanguage());
				pstat.setString(14, resume.getHobby());
				pstat.setString(15, resume.getPractice());
				pstat.setString(16, resume.getPosition());
				pstat.setString(17, resume.getHonor());
				pstat.setString(18, resume.getResearch());
				pstat.setString(19, resume.getSelfevaluation());
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
		return flag;
	}

}
