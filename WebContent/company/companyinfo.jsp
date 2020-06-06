<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企业基本信息</title>
</head>
<body>
    <form method="post" action="/jygl/companyManage" style="margin:0pt;">
      <jsp:include page="/public/info.jsp"></jsp:include>
      <table class="regtable" width="500" align="center" border="0"
      cellpadding="5"  cellspacing="1">
        <tr>
          <td valign="top" width="500" bgcolor="#f9f9f9" height="350">
           <table width="500" align="center" border="0" cellpadding="0" cellspacing="0">
             <tr>
               <td colspan="2" class="tdinfo" height="25">
                  &nbsp;<span style="font-weight:bold">基本信息</span>
               </td>
               <td colspan="2">&nbsp;</td>
             </tr>
             <tr>
               <td colspan="2" width="190">
                 <%
                   Object obj=request.getAttribute("cid");
                    String comid="";
                    if(obj!=null){
                    	 comid=String.valueOf(obj);
                    }
                 %>
                 <input type="hidden" value="<%=comid%>" name="cid">
                 </td>
                 <td colspan="2" width="310">
                    <input type="hidden" value="comregister" name="action">
                 </td>
            </tr>
            <tr>
             <td align="right" height="30" width="190">公司名称：</td>
             <td width="310" align="left">&nbsp;&nbsp;
               <input type="text" name="companyname" size="30"/>
               <br>
               <span>传销，直销，保险，请不要在这里注册</span>
             </td>
            </tr>
            <tr>
              <td align="right" height="30">单位性质:</td>
              <td width="310" align="left">&nbsp;&nbsp;
               <input type="text" name="unitproperty" size="30"/>
             </td>
            </tr>
            <tr>
              <td align="right" height="30">营业执照号：</td>
              <td> &nbsp;&nbsp;
                <input type="text" name="licensenumber" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">所属行业：</td>
              <td>&nbsp;&nbsp;
                <input type="text" name="industry" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">单位规模:</td>
              <td>&nbsp;&nbsp;
               <input type="text" name="unitscale" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">公司地址:</td>
              <td>&nbsp;&nbsp;
               <input type="text" name="address" size="50"/>
              </td>
            </tr>
             <tr>
              <td align="right" height="30">公司网址：</td>
              <td>&nbsp;&nbsp;
                <input type="text" name="webaddress" size="50"/>
              </td>
            </tr>
           <tr>
              <td align="right" height="30">联系人：</td>
              <td>&nbsp;&nbsp;
                <input type="text" name="linkman" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">电话:</td>
              <td>&nbsp;&nbsp;
               <input type="text" name="telephone" size="50"/>
              </td>
            </tr>
             <tr>
              <td align="right" height="30">邮箱：</td>
              <td>&nbsp;&nbsp;
                <input type="text" name="email" size="50"/>
              </td>
            </tr>
             <tr>
              <td align="right" height="30">邮编:</td>
              <td>&nbsp;&nbsp;
                <input type="text" name="postcode" size="50"/>
              </td>
            </tr>
             <td></td>
             <td align="left" height="30"> 
                <input name="imageField" src="/jygl/images/Login_but.gif"
                type="image" style="margin-left:50px"/>
             </td>
           </table>
          </td>
        </tr>
          
      </table>
   </form>
</body>
</html>