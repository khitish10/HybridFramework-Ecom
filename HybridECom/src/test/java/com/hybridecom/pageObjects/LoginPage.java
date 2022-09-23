package com.hybridecom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver){//parameter driver is coming from actual test case
		this.driver=driver;
		PageFactory.initElements(driver, this);//driver passed into the constructor
	}
	
	//Create Object/element methods
	@FindBy(name="Email")
	WebElement txtEmail;
	
	@FindBy(name="Password")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement btnLogin;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement btnLogout;
	
	//Create action methods
	public void setUsername(String uname) {
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}
	
	public void setPassword(String pass) {
		txtPassword.clear();
		txtPassword.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void clickLogout() {
		btnLogout.click();
	}

}
