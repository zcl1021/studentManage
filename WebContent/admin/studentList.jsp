<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	body{
	background-color:#e6e6fa;
	}
	*{
	font-size:12px;
	}

</style>
<title>学生信息管理</title>
</head>
<body>
	<table width="80%" border="1">
	<tr bgcolor="#DCDCDC">
		<th width="20%" height="30px">姓名</th>
		<th width="20%" height="30px">性别</th>
		<th width="20%" height="30px">毕业院校</th>
		<th width="20%" height="30px">专业</th>
		<th width="20%" height="30px">操作</th>
	</tr>
	<c:set var="studentlist" value="${doPage.list}"></c:set>
	<c:forEach items="${studentlist}" var="student">
	<tr>
	<td align="center"  height="26px"><c:out value="${student.sname }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${student.gender }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${student.school }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${student.major }"></c:out></td>
	<td align="center"  height="26px">
	<a href="/jygl/studentManage?action=show&sid=${student.sid}">查看</a>
	<a href="/jygl/studentManage?action=delete&sid=${student.sid}">删除</a>
	</td>
	</tr>
	
	</c:forEach>
	   <div class="pagediv">
              <span>
                 <a href="/jygl/studentManage?action=studentlist&page=1&sql=${doPage.sql }">首页</a>&nbsp;&nbsp;
                 <%--如果当前页面不是第一页，显示上一页选项 --%>
                 <c:if test="${doPage.nowPage>1 }">
                    <a href="/jygl/studentManage?action=studentlist&page=${doPage.nowPage-1 }&sql=${doPage.sql}">上一页</a>
                    &nbsp;&nbsp;
                 </c:if>
                 <%--如果当前页面不是最后一页，显示下一页选项 --%>
                 <c:if test="${doPage.nowPage<doPage.totalPage}">
                    <a href="/jygl/studentManage?action=studentlist&page=${doPage.nowPage+1 }&sql=${doPage.sql}">下一页</a>
                     &nbsp;&nbsp;
                 </c:if>
                   <%--显示末页 --%>
                 
                    <a href="/jygl/studentManage?action=studentlist&page=${doPage.totalPage }&sql=${doPage.sql}">末页</a>
                      &nbsp;&nbsp;
                                   共有${doPage.totalPage}页
                 
              </span>
          </div>
	
	
	</table>
</body>
</html>