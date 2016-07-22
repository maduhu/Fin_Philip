<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib uri ="/struts-tags" prefix ="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户注册</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
}
.STYLE2 {color: #FF0000}
-->
</style>
</head>

<body>
  <div align="center">
  	<s:form action="register">
    <table width="435" height="432" border="0">
      <tr>
        <td colspan="4"><div align="center"><span class="STYLE1">用户信息</span></div></td>
      </tr>
      <tr>
        <td width="113">
        <s:textfield name="user.user_name" label="用户名"/>
        </td>
      </tr>
      <tr>
        <td>真实姓名：
        <label></label></td>
      <td colspan="3"><span class="STYLE2">*</span>
        <input type="text" name="textfield4" /></td>
      </tr>
      <tr>
        <td>用户密码：
        <label></label></td>
      <td colspan="3"><span class="STYLE2">*</span>
        <input type="password" name="textfield42" /></td>
      </tr>
      <tr>
        <td>常用手机：</td>
      <td colspan="3"><span class="STYLE2">*
        <input type="text" name="textfield43" />
        </span></td>
      </tr>
      <tr>
        <td>常用邮箱： 
        <label></label></td>
      <td colspan="3"> <span class="STYLE2">
        &nbsp;&nbsp;<input type="text" name="textfield432" />
        </span></td>
      </tr>
      
      <tr>
        <td height="45">&nbsp;</td>
      <td >&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      <td>
        <s:submit type="button" action="register" label="提交"/>
      </td>
      <td>
        <s:reset type="button" value="重置" />
      </td>
      <td>&nbsp;</td>
      </tr>
    </table>
    </s:form>
  </div>
</body>
</html>