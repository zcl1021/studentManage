<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<title>企业基本信息</title>
<link href="/jygl/css/stucss.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="/jygl/companyManage" method="post">
<table class="regtable" width="500" align="center" border="0" cellpadding="0" cellspacing="0">
			<caption>企业基本信息</caption>		
			<tr>
				<td>
				<input type="hidden" name="action" value="updateCompany">
				</td>
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
						   <input type="hidden" name="cid" id="cid" value="${user.id }">
						   <input type="text" name="companyname" id="companyname" value="${company.companyname}"size="50">
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">单位性质:</td>
						   <td align="left" width="370">
						   <input type="text" name="unitproperty" id="unitproperty" value="${company.unitproperty}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">营业执照号:</td>
						   <td align="left" width="370">
						   <input type="text" name="licensenumber" id="licensenumber" value="${company.licensenumber}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">所属行业</td>
						   <td align="left" width="370">
						   <input type="text" name="industry" id="industry" value="${company.industry}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">单位规模:</td>
						   <td align="left" width="370">
						   <input type="text" name="unitscale" id="unitscale" value="${company.unitscale}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">公司地址:</td>
						   <td align="left" width="370">
						   <input type="text" name="address" id="address" value="${company.address}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">网址:</td>
						   <td align="left" width="370">
						   <input type="text" name="webaddress" id="webaddress" value="${company.webaddress}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">联系人:</td>
						   <td align="left" width="370">
						   <input type="text" name="linkman" id="linkman" value="${company.linkman}"size="50"/>
						   </td>
						</tr>
					    <tr>
						   <td align="right" height="30" width="130">电话:</td>
						   <td align="left" width="370">
						   <input type="text" name="telephone" id="telephone" value="${company.telephone}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">邮箱:</td>
						   <td align="left" width="370">
						   <input type="text" name="email" id="email" value="${company.email}"size="50">
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">邮编:</td>
						   <td align="left" width="370">
						   <input type="text" name="postcode" id="postcode" value="${company.postcode}"size="50">
						   </td>
						</tr>
						<tr>
						 <td colspan="3" align="center">
		                 <input type="submit" value="修改">
						   </td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>
		</form>
</body>
</html>