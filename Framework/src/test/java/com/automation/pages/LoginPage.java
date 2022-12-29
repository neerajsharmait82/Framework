package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	
	
	@FindBy(name="email") WebElement uname;
	
	@FindBy(name="password") WebElement pass;
	
	@FindBy(xpath="//input[@value='Login']") WebElement loginButton;
	
	public void loginToCRM ( String usernameApplication,  String passwordApplication)
	{
		
		driver.findElement(By.partialLinkText("Log")).click();
		uname.sendKeys(usernameApplication);
		pass.sendKeys(passwordApplication);
		driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();
		System.out.println("PASS");
		 
	}

}
