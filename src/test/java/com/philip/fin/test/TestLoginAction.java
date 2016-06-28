package com.philip.fin.test;

import org.apache.struts2.StrutsTestCase;

import com.opensymphony.xwork2.ActionProxy;

public class TestLoginAction extends StrutsTestCase {
	
	public void testLogin() throws Exception {
		
		request.setParameter("user.username", "zhangsan");
		request.setParameter("user.password", "123");
		
		ActionProxy proxy = getActionProxy("/login");
		
		LoginAction loginAction = (LoginAction)proxy.getAction();
		
		String result = proxy.execute();
		
		assertEquals("Should be success","success",result);
	}
}
