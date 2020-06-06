package com.hbsi.dao;

import com.hbsi.bean.Resume;

public interface ResumeDao {
	//定义方法，把简历添加到数据表
		boolean addResume(Resume resume);
}
