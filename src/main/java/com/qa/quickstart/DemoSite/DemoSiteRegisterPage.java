package com.qa.quickstart.DemoSite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteRegisterPage {

	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
	WebElement addUser;
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
	WebElement addPass;
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")
	WebElement loginPage;
	@FindBy(name = "FormsButton2")
	WebElement save;

	public void createUser(String user) {

		addUser.sendKeys(user);

	}

	public void createPass(String pass) {

		addPass.sendKeys(pass);

	}

	public void saveDetails() {

		save.click();
	}

	public void goToLoginPage() {

		loginPage.click();

	}

}
