<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>导航菜单</title>
<link rel="stylesheet" type="text/css" href="/jygl/css/leftmenu.css">
</head>
<body>
	<table width="100%" height="238" border="0" cellpadding="0" bgcolor="#EEF2FB">
	<tr>
		<td width="182" valign="top">
			<div id="container">
				<h1 class="type">
					<a href="javascript:void(0)">学生管理导航</a>
				</h1>
				<div class="content">
				<ul class="menuitem">
					<li>
						<a href="/jygl/public/right.jsp" target="main">学生首页</a>
					</li>
					<li>
						<a href="/jygl/studentManage?action=show" target="main">查看个人基本信息</a>
					</li>
					<li>
						<a href="/jygl/resumeManage?action=create" target="main">制作简历</a>
					</li>
					<li>
						<a href="/jygl/resumeManage?action=edit" target="main">修改简历</a>
					</li>
					<li>
						<a href="/jygl/recruitManage?action=recuit" target="main">查看招聘信息</a>
					</li>
					<li>
						<a href="/jygl/employmentManage?action=list" target="main">查看就业信息</a>
					</li>
					<li>
						<a href="/jygl/public/message.jsp" target="main">给管理员留言</a>
					</li>
					<li>
						<a href="/jygl/public/messageManage?action=list" target="main">查看管理员回复</a>
					</li>
					<li>
						<a href="/jygl/public/updatePassword.jsp" target="main">修改密码</a>
					</li>
				</ul>
			</div>
			
			</div>
		</td>
	</tr>	
</table>
</body>
</html>