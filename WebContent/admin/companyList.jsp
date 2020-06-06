<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企业信息管理</title>
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
		<th width="20%" height="30px">公司名称</th>
		<th width="20%" height="30px">单位性质</th>
		<th width="20%" height="30px">营业执照号</th>
		<th width="20%" height="30px">所属行业</th>
		<th width="20%" height="30px">操作</th>
	</tr>
	<c:set var="companylist" value="${doPage.list}"></c:set>
	<c:forEach items="${companylist}" var="company">
	<tr>
	<td align="center"  height="26px"><c:out value="${company.companyname }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${company.unitproperty }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${company.licensenumber }"></c:out></td>
	<td align="center"  height="26px"><c:out value="${company.industry}"></c:out></td>
	<td align="center"  height="26px">
	<a href="/jygl/companyManage?action=show&cid=${company.cid}">查看</a>
	<a href="/jygl/companyManage?action=delete&cid=${company.cid}">删除</a>
	<a href="/jygl/companyManage?action=create&cid=${company.cid}">发布招聘</a>
	</td>
	</tr>
	
	</c:forEach>
	   <div class="pagediv">
              <span>
                 <a href="/jygl/companyManage?action=companylist&page=1&sql=${doPage.sql }">首页</a>&nbsp;&nbsp;
                 <%--如果当前页面不是第一页，显示上一页选项 --%>
                 <c:if test="${doPage.nowPage>1 }">
                    <a href="/jygl/companyManage?action=companylist&page=${doPage.nowPage-1 }&sql=${doPage.sql}">上一页</a>
                    &nbsp;&nbsp;
                 </c:if>
                 <%--如果当前页面不是最后一页，显示下一页选项 --%>
                 <c:if test="${doPage.nowPage<doPage.totalPage}">
                    <a href="/jygl/companyManage?action=companylist&page=${doPage.nowPage+1 }&sql=${doPage.sql}">下一页</a>
                     &nbsp;&nbsp;
                 </c:if>
                   <%--显示末页 --%>
                 
                    <a href="/jygl/companyManage?action=companylist&page=${doPage.totalPage }&sql=${doPage.sql}">末页</a>
                      &nbsp;&nbsp;
                                   共有${doPage.totalPage}页
                 
              </span>
          </div>
	
	
	</table>
</body>
</html>