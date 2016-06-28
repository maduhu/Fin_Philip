package com.philip.fin.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestUser {
	
	@Test
	public void testUser(){
		User user = new User();
		user.setUsername("Robbin");
		assertEquals(user.getUsername(),"Robbin");
	}
}
