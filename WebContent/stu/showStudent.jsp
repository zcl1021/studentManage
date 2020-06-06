<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生基本信息</title>
<link href="/jygl/css/stucss.css" rel="stylesheet" type="text/css">
</head>
<body>
		<table class="regtable" width="500" align="center" border="0" cellpadding="0" cellspacing="0">
			<caption>学生个人基本信息</caption>		
			<tr>
				<td></td>
			</tr>
			<tr>
				<td valign="top" width="500" bgcolor="#f9f9f9" height="350">
					<table width="500" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" class="tdinfo" height="25">
							<span style="font-weight:bold">学生基本信息</span>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">姓名:</td>
						   <td align="left" width="370">
						   <input type="text" name="sname" value="${studnet.sname}"size="50">
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">性别:</td>
						   <td align="left" width="370">
						   <input type="text" name="gendere" value="${studnet.gender}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">身份证号:</td>
						   <td align="left" width="370">
						   <input type="text" name="idnumber" value="${studnet.idnumber}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">学校:</td>
						   <td align="left" width="370">
						   <input type="text" name="school" value="${studnet.school}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">院系:</td>
						   <td align="left" width="370">
						   <input type="text" name="department" value="${studnet.department}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">专业:</td>
						   <td align="left" width="370">
						   <input type="text" name="major" value="${studnet.major}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">学历:</td>
						   <td align="left" width="370">
						   <input type="text" name="eductaion" value="${studnet.education}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">入学时间:</td>
						   <td align="left" width="370">
						   <input type="text" name="entrancedate" value="${studnet.entrancedate}"size="50"/>
						   </td>
						</tr>
					    <tr>
						   <td align="right" height="30" width="130">籍贯:</td>
						   <td align="left" width="370">
						   <input type="text" name="nativeplace" value="${studnet.nativeplace}"size="50"/>
						   </td>
						</tr>
						
						
					</table>
				</td>
			</tr>
		</table>
</body>
</html>