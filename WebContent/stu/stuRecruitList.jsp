<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>招聘信息管理</title>
<style>
  	body{
	background-color:#e6e6fa;
	}
	*{
	font-size:12px;
	}
</style>
</head>
<body>
  <table width="80%" border="1">
	<tr bgcolor="#DCDCDC">
		<th width="16%" height="30px">公司名称</th>
		<th width="16%" height="30px">招聘人数</th>
		<th width="16%" height="30px">工作地点</th>
		<th width="16%" height="30px">职位性质</th>
		<th width="16%" height="30px">学历要求</th>
		<th width="16%" height="30px">操作</th>
	</tr>
	<c:set var="recruitlist" value="${doPage.list}"></c:set>
	<c:forEach items="${recruitlist}" var="recruit">
	<tr>
	<td align="center"  height="26px"><c:out value="${recruit.companyname }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${recruit.recruitment }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${recruit.workingplace }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${recruit.positiontype}"></c:out></td>
	<td align="center"  height="26px"><c:out value="${recruit.edurequire}"></c:out></td>
	<td align="center"  height="26px">
	<a href="/jygl/recruitManage?action=show&rid=${recruit.rid}">查看</a>
	<a href="/jygl/recruitManage?action=create&rid=${recruit.rid}">投简历</a>
	

	</td>
	</tr>
	
	</c:forEach>
	   <div class="pagediv">
              <span>
                 <a href="/jygl/recruitManage?action=recruitlist&page=1&sql=${doPage.sql }">首页</a>&nbsp;&nbsp;
                 <%--如果当前页面不是第一页，显示上一页选项 --%>
                 <c:if test="${doPage.nowPage>1 }">
                    <a href="/jygl/recruitManage?action=recruitlist&page=${doPage.nowPage-1 }&sql=${doPage.sql}">上一页</a>
                    &nbsp;&nbsp;
                 </c:if>
                 <%--如果当前页面不是最后一页，显示下一页选项 --%>
                 <c:if test="${doPage.nowPage<doPage.totalPage}">
                    <a href="/jygl/recruitManage?action=recruitlist&page=${doPage.nowPage+1 }&sql=${doPage.sql}">下一页</a>
                     &nbsp;&nbsp;
                 </c:if>
                   <%--显示末页 --%>
                 
                    <a href="/jygl/recruitManage?action=recruitlist&page=${doPage.totalPage }&sql=${doPage.sql}">末页</a>
                      &nbsp;&nbsp;
                                   共有${doPage.totalPage}页
                 
              </span>
          </div>
	
	
	</table>
</body>
</html>