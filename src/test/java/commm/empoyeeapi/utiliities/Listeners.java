package commm.empoyeeapi.utiliities;

// this utility file  is created for listener concepts of testng , what listener concept basically will do is based on test post actions this--
// listeners will log the results in extent report
// for e.g if test case fails what is my next action, if test cases are fails what if my next action
// based on test post actions( pass or fails) listeners will add logs in report
// Listener is one of important TestNG feature
// creating extent report

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

    // extent report provides below 3 classes to create report

    public ExtentHtmlReporter htmlReporter; // this class represents the look & fill of report
    public ExtentReports extent; // this class represents sending common information of report
    public ExtentTest test; // this class represents PASS & Failure of report

    public void onStart(ITestContext testContext){

        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");//specify the location of report

       // sending common information in report using ExtentReports class

        htmlReporter.config().setDocumentTitle("Automation Report");//Title of report
        htmlReporter.config().setReportName("Rest API Testing Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name","Employee Database API");
        extent.setSystemInfo("Host Name","local host");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("User","Charan");
    }

    //onTestSuccess method will execute when any of test steps success
    public void onTestSuccess(ITestResult result){

        test=extent.createTest(result.getName());//create new entry in report
        test.log(Status.PASS,"Test Case PASSED IS " + result.getName()); // to add name in extent report
    }

    // this method will execute when test fails
    public void onTestFailure(ITestResult result){

        test=extent.createTest(result.getName());//create new entry in report
        test.log(Status.FAIL,"Test Case FAILED IS " + result.getName()); // to add name in extent report
        test.log(Status.FAIL,"Test Case FAILED IS " + result.getThrowable()); // to add error/exception in extent report
    }

    //// this method will execute when test skips
    public void onTestSkipped(ITestResult result){

        test=extent.createTest(result.getName());//create new entry in report
        test.log(Status.SKIP,"Test Case SKIPPED IS " + result.getName()); // to add name in extent report
    }

    // this method will arrange all the steps and refreshing the report at end
    public void onFinish(ITestResult result){

        extent.flush();
    }
}
