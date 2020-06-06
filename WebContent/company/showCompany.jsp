<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企业基本信息</title>
<link href="/jygl/css/stucss.css" rel="stylesheet" type="text/css">
</head>
<body>
  <table class="regtable" width="500" align="center" border="0" cellpadding="0" cellspacing="0">
			<caption>企业基本信息</caption>		
			<tr>
				<td></td>
			</tr>
			<tr>
				<td valign="top" width="500" bgcolor="#f9f9f9" height="350">
					<table width="500" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" class="tdinfo" height="25">
							<span style="font-weight:bold">企业基本信息</span>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">公司名称:</td>
						   <td align="left" width="370">
						   <input type="text" name="sname" value="${company.companyname}"size="50">
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">单位性质:</td>
						   <td align="left" width="370">
						   <input type="text" name="gendere" value="${company.unitproperty}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">营业执照号:</td>
						   <td align="left" width="370">
						   <input type="text" name="idnumber" value="${company.licensenumber}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">所属行业</td>
						   <td align="left" width="370">
						   <input type="text" name="school" value="${company.industry}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">单位规模:</td>
						   <td align="left" width="370">
						   <input type="text" name="department" value="${company.unitscale}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">公司地址:</td>
						   <td align="left" width="370">
						   <input type="text" name="major" value="${company.address}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">网址:</td>
						   <td align="left" width="370">
						   <input type="text" name="eductaion" value="${company.webaddress}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">联系人:</td>
						   <td align="left" width="370">
						   <input type="text" name="entrancedate" value="${company.linkman}"size="50"/>
						   </td>
						</tr>
					    <tr>
						   <td align="right" height="30" width="130">电话:</td>
						   <td align="left" width="370">
						   <input type="text" name="nativeplace" value="${company.telephone}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">邮箱:</td>
						   <td align="left" width="370">
						   <input type="text" name="sname" value="${company.email}"size="50">
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">邮编:</td>
						   <td align="left" width="370">
						   <input type="text" name="sname" value="${company.postcode}"size="50">
						   </td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>
</body>
</html>