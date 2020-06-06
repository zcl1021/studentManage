package com.hbsi.bean;

	public class Message {
	   private int mid;//留言id
	   private int id;//用户的id
	   private String username;//用户名
	   private String title;//留言标题
	   private String msgtime;//留言时间
	   private String content;//留言内容
	   private String reply;//管理员回复
	   private String replytime;//管理员回复时间
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(String msgtime) {
		this.msgtime = msgtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplytime() {
		return replytime;
	}
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	   
	}
