<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>招聘信息</title>
<link href="/jygl/css/stucss.css" rel="stylesheet" type="text/css">
</head>
<body>
 <table class="regtable" width="500" align="center" border="0" cellpadding="0" cellspacing="0">
			<caption>招聘信息</caption>		
			<tr>
				<td></td>
			</tr>
			<tr>
				<td valign="top" width="500" bgcolor="#f9f9f9" height="350">
					<table width="500" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" class="tdinfo" height="25">
							<span style="font-weight:bold">招聘信息</span>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">公司名称:</td>
						   <td align="left" width="370">
						   <input type="text" name="companyname" value="${recruit.companyname}"size="50">
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">公司地址:</td>
						   <td align="left" width="370">
						   <input type="text" name="companyname" value="${recruit.companyname}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">单位邮编：</td>
						   <td align="left" width="370">
						   <input type="text" name="postcode" value="${recruit.postcode}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">招聘人数</td>
						   <td align="left" width="370">
						   <input type="text" name="recruitment" value="${recruit.recruitment}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">工作地点:</td>
						   <td align="left" width="370">
						   <input type="text" name="workingplace" value="${recruit.workingplace}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">职位性质:</td>
						   <td align="left" width="370">
						   <input type="text" name="positiontype" value="${recruit.positiontype}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">学历要求:</td>
						   <td align="left" width="370">
						   <input type="text" name="edurequire" value="${recruit.edurequire}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">职位描述:</td>
						   <td align="left" width="370">
						   <input type="text" name="description" value="${recruit.description}"size="50"/>
						   </td>
						</tr>
					    <tr>
						   <td align="right" height="30" width="130">使用部门:</td>
						   <td align="left" width="370">
						   <input type="text" name="branch" value="${recruit.branch}"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">联系人:</td>
						   <td align="left" width="370">
						   <input type="text" name="linkman" value="${recruit.linkman}"size="50">
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">联系电话:</td>
						   <td align="left" width="370">
						   <input type="text" name="telephone" value="${recruit.telephone}"size="50">
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">单位主页:</td>
						   <td align="left" width="370">
						   <input type="text" name="hostpage" value="${recruit.hostpage}"size="50">
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">邮箱:</td>
						   <td align="left" width="370">
						   <input type="text" name="email" value="${recruit.email}"size="50">
						   </td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>
</body>
</html>