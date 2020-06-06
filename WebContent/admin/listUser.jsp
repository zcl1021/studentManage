<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<style  type="text/css">
   .ulist{
      font-size:12px;
      font-weight:bold;
      linr-height:20px;
      
   }
   .btnverify{
       margin-top:14px;
    }
    .usertable .t1{
       font-size:12px;
       margin-top:15px;
    }
    .t1 tr{
        line-height:30px;
        font-weight:bold;
        
    }
    .t1 td{
        text-align:center;
    }
    .pagediv{
        float:right;
        font-size:12px;
        margin-right:180px;
    }
</style>
 <script type="text/javascript">
   function sub(){
	   //获取表单中action输入框对象
    var eleaction = document.getElementyById("action");
    eleaction.value="list";
    var opvalue=document.getElementyById("userselected").value;
   //设置隐藏输入框sql的值是选择框的选项的值
    document.getElementByid("sql").value=opvalue;
    //提交表单
    document.flist.submit()
}
 </script>
 </head>
<body>
<form action="/jygl.userManage" method="post" name="flist">
<input type="hidden" name="action" id="action" value="">
<input type="hidden" name="sql" id="sql" value="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
      <td valign="top" background="/jygl/images/mail_leftbg.gif" width="17">
          <image alt="*" src="/jygl/images/left-top-right.gif" width="17">
       </td>
       <td valign="middle" background="/jygl/images/content-bg.gif">
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                 <td><div class="ulist">用户列表</div></td>
              </tr>
           </table>
       </td>
       <td width="16" backgrounf="/jygl/images/mail_rightbg.gif">
          <img alt="*" src="/jygl/images/nav-right-bg.gif"> 
       
       </td>
       </tr>
       <tr>
       <td valign="top" background="/jygl/images/mail_leftbg.gif" width="17">
          &nbsp;
       </td>
       <td>
           <!-- 使用div块显示高级查询模块 -->
           <div>
            <span class="ulist"><strong>用户管理</strong>(共${doPage.count}个用户)</span>
           </div>
           <div class="btnverify">
              <select class="sec" name="userselected" id="userselected" onchange="sub()">
              <option value="">审核状态</option>
              <option value="1">未审核</option>
              <option value="2">已审核</option>
              <option value="3">审核未通过</option>
                 
              </select>
           </div>
           <div class="usertable">
              <table  class="t1" border="0" cellpadding="0" cellspacing="0" width="100%">
                  <tr>
                      <th width="20"></th>
                      <th width="100">ID</th>
                      <th width="150">用户名</th>
                      <th width="200">密码</th>
                      <th width="150">用户类型</th>
                      <th>操作</th>
                  </tr>
                  <%--定义一个变量userlist，变量的值是请求属性dopage值的list属性的值 --%>
                  <c:set var="userlist" value="${doPage.list}"></c:set>
                  <%--使用foreach循环遍历userlist集合，因为userlist是user对象的集合，
                                 所以在循环中定义一个临时变量user，每次循环取出集合中一个元素赋值给user --%>
                  <c:forEach var="user" items="${userlist}">
                      <tr>
                        <td></td>
                        <td><c:out value="${user.id}"></c:out></td>
                        <td><c:out value="${user.username}"></c:out></td>
                        <td><c:out value="${user.password}"></c:out></td>
                        <td><c:out value="${user.usertypes}"></c:out></td>
                        <td>
                           <c:if test="${user.verify== '1'}">
                               <a href="/jygl/userManage?action=active&id=${user.id}">审核通过</a>|
                               <a href="/jygl/userManage?action=invalid&id=${user.id}">审核未通过</a>
                           </c:if>
                            <c:if test="${user.verify== '2'}">
                                <a href="/jygl/userManage?action=disable&id=${user.id}">禁用</a>
                           </c:if>
                              <c:if test="${user.verify != '3'}">
                                <a href="/jygl/admin/editUser.jsp?id=${user.id}">修改密码</a>
                           </c:if>
                           <a href="/jygl/userManage?action=delete&id=${user.id}">删除</a>
                        </td>
                      </tr>
                  </c:forEach>
              </table>
             
           </div>
          <%--画出显示分页的上一页，下一页等信息 --%>
          <div class="pagediv">
              <span>
                 <a href="/jygl/userManage?action=list&page=1&sql=${doPage.sql }">首页</a>&nbsp;&nbsp;
                 <%--如果当前页面不是第一页，显示上一页选项 --%>
                 <c:if test="${doPage.nowPage>1 }">
                    <a href="/jygl/userManage?action=list&page=${doPage.nowPage-1 }&sql=${doPage.sql}">上一页</a>
                    &nbsp;&nbsp;
                 </c:if>
                 <%--如果当前页面不是最后一页，显示下一页选项 --%>
                 <c:if test="${doPage.nowPage<doPage.totalPage}">
                    <a href="/jygl/userManage?action=list&page=${doPage.nowPage+1 }&sql=${doPage.sql}">下一页</a>
                     &nbsp;&nbsp;
                 </c:if>
                   <%--显示末页 --%>
                 
                    <a href="/jygl/userManage?action=list&page=${doPage.totalPage }&sql=${doPage.sql}">末页</a>
                      &nbsp;&nbsp;
                                   共有${doPage.totalPage}页
                 
              </span>
          </div>
       </td>
       <td width="16" backgrounf="/jygl/images/mail_rightbg.gif">
          &nbsp;
       </td>
       <tr/>
       <tr>
       <td valign="top" background="/jygl/images/mail_leftbg.gif" width="17">
          <img alt="*" src="/jygl/images/buttom_left2.gif">
       </td>
       <td background="/jygl/images/buttom_bgs.gif">
           <img alt="*" src="/jygl/images/buttom_bgs.gif">
       </td>
       <td width="16" backgrounf="/jygl/images/mail_rightbg.gif">
          <img alt="*" src="/jygl/images/buttom_right2.gif">
       </td>
       </tr>

</table>

</form>
</body>
</html>