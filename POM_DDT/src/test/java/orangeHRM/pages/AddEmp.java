package orangeHRM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddEmp {
	WebDriver driver;
	public AddEmp(WebDriver driver)
	{
		this.driver=driver;
	}
//define Repository
	@FindBy(xpath = "//b[normalize-space()='PIM']")
	WebElement Click_Pim;
	@FindBy(id = "btnAdd")
	WebElement Click_Add;
	@FindBy(name = "firstName")
	WebElement FirstName;
	@FindBy(name = "middleName")
	WebElement Enter_Mname;
	@FindBy(name = "lastName")
	WebElement Enter_Lname;
	@FindBy(name = "employeeId")
	WebElement empid;
	@FindBy(id="btnSave")
	WebElement Click_save;
	@FindBy(id = "menu_pim_viewEmployeeList")
	WebElement Click_EmployeeList;
	@FindBy(id="empsearch_id")
	WebElement Enter_Empid;
	@FindBy(id = "searchBtn")
	WebElement Click_Search;
	@FindBy(id = "resultTable")
	WebElement WebTable;
	//method for add employee
	public boolean validate_Emp(String FirstName,String middlename,String lastname) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(Click_Pim).click().perform();
		ac.moveToElement(Click_Add).click().perform();
		this.FirstName.sendKeys(FirstName);
		Enter_Mname.sendKeys(middlename);
		Enter_Lname.sendKeys(lastname);
		String Exp_Empid =empid.getAttribute("value");
		Click_save.click();
		Click_EmployeeList.click();
		Thread.sleep(2000);
		Enter_Empid.sendKeys(Exp_Empid);
		Click_Search.click();
		//get rows collection
		List<WebElement> rows = WebTable.findElements(By.tagName("tr"));
			boolean flag = false;
			for(int i=1;i<rows.size();i++)
			{
				List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
				if(cols.get(1).getText().equals(Exp_Empid))
				{
					flag = true;
					break;
				}
			}
			if(flag)
			{
				return true;
			}
			else
				return false;
			
		}
		
		
		
	}
	

