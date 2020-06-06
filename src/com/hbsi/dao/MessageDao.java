package com.hbsi.dao;

import com.hbsi.bean.Message;

public interface MessageDao {
   //定义方法，把用户留言信息添加到message表
	boolean addMessage(Message message);
}
