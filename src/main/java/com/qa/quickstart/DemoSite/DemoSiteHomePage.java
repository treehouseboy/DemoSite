package com.qa.quickstart.DemoSite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoSiteHomePage {
	
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]") private WebElement addClick;
	
	public void goToAddPage() {
		addClick.click();
	}

}
