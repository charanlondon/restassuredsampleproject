package common.employeeapi.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;


public class TestBase {

    // Below 2 statements putting in base class instead of putting every test cases because they are common to all test cases
    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID="18";//Hard coded- Input for get details of single employee & update employee

    public Logger logger;

    @BeforeClass
//     this setup metod basically generate the logs, this method will execute before class
//    this setup metod is common for every test case and execute first before any extended class
    public void setup(){

        logger=Logger.getLogger("EmployeeRestAPI");// usually getLogger should be name of class  or different name
        PropertyConfigurator.configure("Log4j.properties");// specify the location of Log4j properties file
        logger.setLevel(Level.DEBUG);
    }
}
