<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri ="/struts-tags" prefix ="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>贷款申请II</title>
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
<s:form action="loan_save"> <!-- enctype="multipart/form-data"  -->
<table width="1311" height="486" border="1">
  <tr>
    <td width="303"><span class="STYLE1">贷款信息</span></td>
    <td width="257">&nbsp;</td>
    <td width="317">&nbsp;</td>
    <td width="406">&nbsp;</td>
  </tr>
  <tr>
    <td>贷款卡
    <input type="file" name="file" /></td>
    <td>
      <s:textfield type="input" name="loan_card" label="贷款卡卡号"/>
      </td>
    <td>
      <s:textfield type="input" name="loan_card_bank" label="贷款卡开户行"/>
      ></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">会计师出具的验资报告 
      <label>
      <input type="file" name="file2" />
      </label></td>
    <td>公司章程 
      <label>
      <input type="file" name="file3" />
      </label></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">法定代表人身份证及个人简历 
      <label>
      <input type="file" name="file4" />
      </label></td>
    <td colspan="2">公司前三年度的财务报表
      <label>
      <input type="file" name="file5" />
      </label></td>
    </tr>
  <tr>
    <td colspan="2"><label>税务部门确认的报税表、完税凭证 
      <input type="file" name="file6" />
    </label></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4">
      <s:textarea name="loan_investigation" cols="80" rows="5" label="公司在我处及其他银行贷款的基本情况以及授信情况 "/>
      </td>
    </tr>
  <tr>
    <td colspan="4"><hr style="height:2px; border:none; border-top:2px dotted;" /></td>
    </tr>
  <tr>
    <td colspan="4">
      <s:textarea name="loan_usage" cols="80" rows="5" label="贷款用途 "/></td>
    </tr>
  <tr>
    <td colspan="2">相关购销合同 
      <label>
      <input type="file" name="file7" />
      </label></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="39">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><div align="right">
      <input type="button" name="Submit" value="上一步" onclick="window.location.href('./loan_apply_company.html')"/>
    </div></td>
    <td>
      <input type="button" name="Submit2" value="下一步" onclick="window.location.href('./loan_apply_credit.html')" />
    </td>
    <td>&nbsp;</td>
  </tr>
</table>
</s:form>
</body>
</html>