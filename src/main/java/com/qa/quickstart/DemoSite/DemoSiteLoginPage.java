package com.qa.quickstart.DemoSite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteLoginPage {
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
	WebElement user;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
	WebElement pass;
	@FindBy(name = "FormsButton2")
	WebElement submit;
	
	public void insertUser(String user) {
		
		this.user.sendKeys(user);
	}
	
	public void insertPass(String pass) {
		
		this.pass.sendKeys(pass);
		
	}
	
	public void submitDetails() {
		
		submit.click();
		
	}
	
	

}
