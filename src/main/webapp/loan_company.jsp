<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri ="/struts-tags" prefix ="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>贷款信息</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 16px;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<s:form action="loan_save">
  <table width="957" height="459" border="1">
  <tr>
    <td colspan="2"><span class="STYLE1">申请人基本情况</span></td>
    <td colspan="2">&nbsp;</td>
    <td width="221"><s:hidden name="company.loan_id" /></td>
    <td width="197">&nbsp;</td>
  </tr>
  <tr>
    <td width="67">公司名称  </td>
    <td ><label><s:textfield name="company.company_name" /></label></td>
    <td colspan="3"><label>营业地点</label> 
      <label>
      <s:textfield name="company.company_address" size="50" />
      </label></td>
    <td>公司类型 
      <label>
      <s:select name="company.company_type" list="#{1:'国有企业',2:'民营企业',3:'外商独资企业',4:'中外合资企业' }">
      </s:select>
      </label></td>
  </tr>
  <tr>
    <td height="91">主营业务</td>
    <td colspan="5">
      <s:textarea name="company.business" cols="70" rows="5" />
    </td>
    </tr>
  <tr>
    <td>营业执照</td>
    <td colspan="2"><label>
      <input type="file" name="file" />
    </label></td>
    <td colspan="2">组织机构代码证 
      <label>
      <input type="file" name="file2" />
      </label></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>税务证</td>
    <td colspan="2"><label>
      <input type="file" name="file3" />
    </label></td>
    <td colspan="2">开户许可证 
      <label>
      <input type="file" name="file4" />
      </label></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>注册资本
      <label></label></td>
    <td><s:textfield name="company.reg_capital" /></td>
    <td width="78"><label>法定代表人 
      
    </label></td>
    <td width="153"><s:textfield name="company.representive" size="20" /></td>
    <td>员工人数 
      <label>
      <s:textfield name="company.employee_num" size="20" />
      </label></td>
    <td>成立时间 
      <label>
      <select name="select2">
        <option value="1">2004</option>
        <option value="2">2005</option>
        <option value="3">2006</option>
      </select>
      </label></td>
  </tr>
  <tr>
    <td>联系人</td>
    <td><label>
      <input type="text" name="textfield4" />
    </label></td>
    <td>手机    
      <label></label></td>
    <td><input name="textfield5" type="text" size="20" /></td>
    <td>固定电话 
      <label>
      <input name="textfield7" type="text" size="20" />
      </label></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
      <s:submit type="button" name="action:loan_save" value="保存"/>
      <s:submit type="button" name="action:loan_next" value="保存&下一步"/>
    </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</s:form>
</body>
</html>