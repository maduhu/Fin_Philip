<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>贷款信息III</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 18px;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<form action="" method="post" enctype="multipart/form-data" name="form1" id="form1">
<table width="1336" height="468" border="1">
  <tr>
    <td width="363"><span class="STYLE1">贷款信用增强</span></td>
    <td width="330">&nbsp;</td>
    <td width="141">&nbsp;</td>
    <td width="474">&nbsp;</td>
  </tr>
  <tr>
    <td height="48">是否有抵押物 
      
        <label>
          <input type="radio" name="radiobutton" value="radiobutton" />
        </label>
    是 
    <label>
    <input type="radio" name="radiobutton" value="radiobutton" />
    否</label></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>抵押物的证明文件 
      <label>
      <input type="file" name="file" />
      </label></td>
    <td>评估报告 
      <input type="file" name="file2" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="42">是否有担保人 
      <label>
      <input type="radio" name="radiobutton" value="radiobutton" />
      </label>
是
<label>
<input type="radio" name="radiobutton" value="radiobutton" />
否</label></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>担保人名称 
      <label>
      <input type="text" name="textfield" />
      </label></td>
    <td>同前面的公司信息</td>
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
  <tr>
    <td>&nbsp;</td>
    <td><label>
      <div align="right">
        <input type="button" name="Submit" value="上一步" onclick="window.location.href('./loan_apply_loan.html')"/>
        </div>
    </label></td>
    <td><label>
      <input type="button" name="Submit2" value="完成提交" />
    </label></td>
    <td>&nbsp;</td>
  </tr>
</table>
<label></label>
</form>
</body>
</html>
