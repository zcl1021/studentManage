<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布招聘信息信息</title>
<link href="/jygl/css/stucss.css" rel="stylesheet" type="text/css">
<body>
  <form action="/jygl/recruitManage" method="post">
<table class="regtable" width="500" align="center" border="0" cellpadding="0" cellspacing="0">
			<caption>招聘信息</caption>		
			<tr>
				<td>
				<input type="hidden" name="action" value="createRecruit">
				</td>
			</tr>
			<tr>
				<td valign="top" width="500" bgcolor="#f9f9f9" height="350">
					<table width="500" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr>
						   <td align="right" height="30" width="130">单位名称:</td>
						   <td align="left" width="370">
						   <input type="hidden" name="cid" id="cid" value="${company.cid }">
						   <input type="text" name="companyname" id="companyname" value="${company.companyname}"size="50">
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">单位地址:</td>
						   <td align="left" width="370">
						   <input type="text" name="address" id="address" value="${company.address}"size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">邮政编码:</td>
						   <td align="left" width="370">
						   <input type="text" name="postcode" id="postcode" size="50">
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">招聘人数</td>
						   <td align="left" width="370">
						   <input type="text" name="recruitment" id="recruitment" size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">工作地点:</td>
						   <td align="left" width="370">
						   <input type="text" name="workingplace" id="workingplace" size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">职位性质:</td>
						   <td align="left" width="370">
						     <select name="positiontype" id="positiontype">
                                  <option value="00" selected="selected">请选择</option>
                                  <option value="全职" >全职</option>
                                  <option value="兼职" >兼职</option>
                                  <option value="实习" >实习</option>
                             </select>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">学历要求:</td>
						   <td align="left" width="370">
						   <input type="text" name="edurequire" id="edurequire" "size="50"/>
						   </td>
						</tr>
							<tr>
						   <td align="right" height="30" width="130">职位描述及要求:</td>
						   <td align="left" width="370">
						   <textarea rows="3" cols="45" name="description" id="description"></textarea> 
						   </td>
						</tr>
						   
						<tr>
						   <td align="right" height="30" width="130">使用部门:</td>
						   <td align="left" width="370">
						   <input type="text" name="branch" id="branch" size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">联系人:</td>
						   <td align="left" width="370">
						   <input type="text" name="linkman" id="linkman" size="50"/>
						   </td>
						</tr>
					    <tr>
						   <td align="right" height="30" width="130">电话:</td>
						   <td align="left" width="370">
						   <input type="text" name="telephone" id="telephone"size="50"/>
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">单位主页:</td>
						   <td align="left" width="370">
						   <input type="text" name="hostpage" id="hostpage" size="50">
						   </td>
						</tr>
						<tr>
						   <td align="right" height="30" width="130">邮箱:</td>
						   <td align="left" width="370">
						   <input type="text" name="email" id="email"size="50">
						   </td>
						</tr>
					
						<tr>
						 <td colspan="3" align="center">
		                 <input type="submit" value="发布">
						   </td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>
		</form>
</body>
</html>