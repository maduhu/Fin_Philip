<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri ="/struts-tags" prefix ="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>账户信息</title>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000;
	font-weight: bold;
	font-size: 30px;
}
.STYLE3 {
	font-size: 18px;
	font-weight: bold;
}
.STYLE4 {font-size: 16px}
-->
</style>
</head>

<body>
<table width="790" height="321" border="0">
  <tr>
    <td width="208">总资产 <span class="STYLE1">200</span>元 </td>
    <td width="184">&nbsp;</td>
    <td width="345">&nbsp;</td>
    <td width="35">&nbsp;</td>
  </tr>
  <tr>
    <td>昨日收益 <span class="STYLE3">0.02</span> 元 </td>
    <td>累计已收收益 <span class="STYLE3">200.11</span> 元 </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4"><hr style="height:2px; border:none; border-top:2px dotted;" /></td>
  </tr>
  <tr>
    <td>可用余额</td>
    <td>鹏元宝</td>
    <td>投资券</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><span class="STYLE3"><s:property value="balance.formattedAccount_bal" /></span> 元 </td>
    <td><span class="STYLE3">175.63 </span>元</td>
    <td><span class="STYLE3">30</span></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><label>
      <s:form action="deposit" >
      <s:submit type="button" value="充值 " />
      </s:form>
      <s:form action="draw" >
      <s:submit type="button" value="取现" />
      </s:form>
    </label></td>
    <td><label>
      <input type="submit" name="Submit3" value="转出" />
      <input type="submit" name="Submit4" value="管理" />
    </label></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4"><hr style="height:2px; border:none; border-top:2px dotted;" /></td>
  </tr>
  <tr>
    <td><span class="STYLE4">我的理财 <span class="STYLE3">34.48 </span> </span>元</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
