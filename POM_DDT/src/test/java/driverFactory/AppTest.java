package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import orangeHRM.pages.AddEmp;
import util.AppUtil;
import util.ExcelFileUtil;

public class AppTest extends AppUtil{
String inputpath ="./Fileinput/Employee.xlsx";
String outputpath ="./FileOutput/AddEmpResults.xlsx";
ExtentReports reports;
ExtentTest logger;
String TCSheet ="EmpData";
@Test
public void startTest() throws Throwable
{
	//define path to generate html report under target folder
	reports= new ExtentReports("./target/ExtentReopts/Emp.html");
	//create reference object for ExcelFileUtil class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in TCSheet
	int rc = xl.rowCount(TCSheet);
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		logger = reports.startTest("Validate Emp");
		logger.assignAuthor("Ranga");
		logger.assignCategory("Multiple Data");
		String fname = xl.getCellData(TCSheet, i, 0);
		String mname = xl.getCellData(TCSheet, i, 1);
		String lname = xl.getCellData(TCSheet, i, 2);
		logger.log(LogStatus.INFO, fname+"   "+mname+"   "+lname);
		//call AddEmp page class
		AddEmp emp = PageFactory.initElements(driver, AddEmp.class);
		boolean res =emp.validate_Emp(fname, mname, lname);
		if(res)
		{
			xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
			logger.log(LogStatus.PASS, "Employee Id Found in Table");
		}
		else
		{
			xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
			logger.log(LogStatus.FAIL, "Employee Id Not Found in Table");
		}
		reports.endTest(logger);
		reports.flush();
	}
}
}















