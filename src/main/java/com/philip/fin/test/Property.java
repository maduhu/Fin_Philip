package com.philip.fin.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			String userDir = System.getProperty("user.dir");
			input = new FileInputStream(userDir + "/config.properties");
			
			prop.load(input);
			
			System.out.println("the user is: " + prop.getProperty("user") + ",");
			System.out.println("The password is: " + prop.getProperty("password"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
