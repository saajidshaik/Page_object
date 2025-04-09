package orangeHRM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdminLogout {
	WebDriver driver;
	public AdminLogout(WebDriver driver)
	{
		this.driver=driver;
	}
@FindBy(xpath = "//a[@id='welcome']")
WebElement click_Welcome;
@FindBy(xpath = "//a[normalize-space()='Logout']")
WebElement click_Logout;
public void logout()
{
	Actions ac = new Actions(driver);
	ac.moveToElement(click_Welcome).click().perform();
	ac.pause(5000);
	ac.moveToElement(click_Logout).click().perform();
}

}
