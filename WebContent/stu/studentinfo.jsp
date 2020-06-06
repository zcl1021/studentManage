<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生基本信息</title>
</head>
<body>
   <form method="post" action="/jygl/studentManage" style="margin:0pt;">
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
                   Object obj=request.getAttribute("sid");
                    String stuid="";
                    if(obj!=null){
                    	 stuid=String.valueOf(obj);
                    }
                 %>
                 <input type="hidden" value="<%=stuid%>" name="sid">
                 </td>
                 <td colspan="2" width="310">
                    <input type="hidden" value="sturegister" name="action">
                 </td>
            </tr>
            <tr>
             <td align="right" height="30" width="190">姓名：</td>
             <td width="310" align="left">&nbsp;&nbsp;
               <input type="text" name="sname" size="30"/>
             </td>
            </tr>
            <tr>
              <td align="right" height="30">性别</td>
              <td>&nbsp;&nbsp;
               <input name="gender" type="radio" value="男" checked style="border:0;"/>
                            男&nbsp;
                <input name="gennder" type="radio" value="女" style="border:0;"/>
                            女&nbsp;
               </td>
            </tr>
            <tr>
              <td align="right" height="30">身份证号：</td>
              <td> &nbsp;&nbsp;
                <input type="text" name="idnumber" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">学校：</td>
              <td>&nbsp;&nbsp;
                <input type="text" name="school" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">院系:</td>
              <td>&nbsp;&nbsp;
               <input type="text" name="department" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">专业:</td>
              <td>&nbsp;&nbsp;
               <input type="text" name="major" size="50"/>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">学历:</td>
              <td>&nbsp;&nbsp;
               <select name="education">
                <option value="00" selected="selected">请选择</option>
                <option value="专科" >专科</option>
                <option value="本科" >本科</option>
                <option value="硕士研究生" >硕士研究生</option>
                <option value="博士研究生"> 博士研究生</option>
               </select>
              </td>
            </tr>
            <tr>
              <td align="right" height="30">入学时间:</td>
              <td>&nbsp;&nbsp;
               <input type="text" name="entrancedate" style="width:100%" onfocus="HS_setDate(this)" />
              </td>
            </tr>
            <tr>
              <td align="right" height="30">籍贯:</td>
              <td>&nbsp;&nbsp;
               <input type="text" name="nativeplace" size="50"/>
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