<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>生成简历</title>
		<style type="text/css">
		  td{
		    font-size:12px;
		  }
		</style>
	</head>
    <script type="text/javascript" src="/jygl/js/datepicker.js" ></script>
	<body bgcolor="eff2fb">
		<div id="main">
			<form action="/jygl/resumeManage" method="post">
			<c:set var="studentlist" value="${doPage.list}"></c:set>
	        <c:forEach items="${studentlist}" var="student">
	       </c:forEach>
				<input type="hidden" name="action" value="add" />
				<input type="hidden" name="sid" value="${student.sid}" />
				<div id="top">
					<div id="btn">
						<div id="wenzi">
							简历信息
						</div>
						(*为必填项)
					</div>
				</div>
				<table width="80%" border="0">
					<tr>
						<td width="15%">
							*姓名：
						</td>
						<td width="35%">
							<input type="text" name="sname"  value="${student.sname}" readonly/>
						</td>
						<td width="15%">
							*性别：							
						</td>
						<td width="35%" height="11">							
							<input type="text" name="gender" value="${student.gender}" readonly/>								
						</td>						
					</tr>
					<tr>
						<td>
							*出生日期：
						</td>
						<td>
							<input type="text" name="birthdate" style="width:70px" onfocus="HS_setDate(this)" >
						</td>
						<td>							
							*民族：
						</td>
						<td>
				
						   <input type="nation" name="nation"   />
                 
						</td>
					</tr>
					<tr>
						<td>
							*政治面貌：
						</td>
						<td>							
							<input type="text" name="politics" />							
						</td>
						<td>
							毕业时间：
							<label></label>
						</td>
						<td>
						    <input type="text" name="graduation" style="width:70px" onfocus="HS_setDate(this)" >							
						</td>
					</tr>
					<tr>
						<td>
							*毕业院校：
						</td>
						<td>
							<input type="text" name="school" value="${student.school}" readonly />
						</td>
						<td>
						    *所学专业：
						</td>
						<td>
						  <input type="text" name="major" value="${student.major}" readonly />
						</td>
					</tr>
					<tr>
						<td>
							*学历：
						</td>
						<td>
							<input type="text" name="education" value="${student.education}" readonly />
						</td>
						<td>
						    *邮箱：
						</td>
						<td>
						  <input type="text" name="email" id="email"/>
						</td>
					</tr>
					<tr>
						<td>
							*联系电话：
						</td>
						<td>
							<input type="text" name="phone"  id="phone" />
						</td>
						<td>
						    *外语水平：
						</td>
						<td>
						  <input type="text" name="foreignlanguage"  />
						</td>
					</tr>
					<tr>
						<td>
							特长及爱好：
						</td>						
						<td colspan="3">
							<textarea name="hobby" rows="3" cols="60"></textarea>
						</td>												
					</tr>
					<tr>
						<td>
							社会实践经历：
						</td>
						<td colspan="3">
							<textarea name="practice" rows="3" cols="60"></textarea>
						</td>						
					</tr>					
					<tr>
						<td>
							在学校担任职务：
						</td>						
						<td colspan="3">
							<textarea name="position" rows="3" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							在校期间获奖：
						</td>						
						<td colspan="3">
							<textarea name="honor" rows="3" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							科研成果：
						</td>						
						<td colspan="3">
							<textarea name="research" rows="3" cols="60"></textarea>
						</td>
					</tr>												
					<tr>
						<td>
							自我评价：
						</td>
						<td colspan="3" align="left">

							<textarea name="selfevaluation" cols="60" rows="3"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div align="center">
								<input type="submit" name="Sumbit" value="提交" />
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
