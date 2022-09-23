package com.hybridecom.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	
	public ReadConfig()  {
		
		//Loads the configuration file during run time
		try {
			File file = new File("./configuration/config.properties");//read the file and store it inside file reference variable
			FileInputStream inputStream = new FileInputStream(file);
			prop = new Properties();
			prop.load(inputStream);//load the config file
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//For each variable in config file create separate method to extract data from config file
	public String getApplicationURL() {
		String baseURL = prop.getProperty("baseURL");
		return baseURL;
	}
	
	public String getuserEmail() {
		String userEmail=prop.getProperty("userEmail");
		return userEmail;
	}
	
	public String getpassword() {
		String password=prop.getProperty("password");
		return password;
	}
	
	public String getchromepath() {
		String chromepath=prop.getProperty("chromepath");
		return chromepath;
	}
	
	public String getfirefoxpath() {
		String firefoxpath = prop.getProperty("firefoxpath");
		return firefoxpath;
	}

}
