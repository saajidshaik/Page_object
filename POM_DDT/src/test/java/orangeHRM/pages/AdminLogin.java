package orangeHRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AdminLogin {
//define Repository
	@FindBy(name = "txtUsername")
	WebElement Enter_User;
	@FindBy(id ="txtPassword")
	WebElement Enter_Pass;
	@FindBy(name ="Submit")
	WebElement Click_Login;
	@FindBy(xpath = "//b[normalize-space()='Admin']")
	WebElement Admin_Link;
	//write void type method to perform action
	public void adminLogin(String user,String pass)
	{
		Enter_User.clear();
		Enter_User.sendKeys(user);
		Enter_Pass.clear();
		Enter_Pass.sendKeys(pass);
		Click_Login.click();
		if(Admin_Link.isDisplayed())
		{
			Reporter.log("Login Success",true);
		}
		else
		{
			Reporter.log("Login Fail",true);
		}
	}
	
}
