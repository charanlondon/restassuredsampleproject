package commonn.employeeapi.testcases;

import common.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_Get_Single_Employee_Record extends TestBase {

    @BeforeClass
    void getEmployeeData() {

        logger.info("****************** Started TC002_Get_Single_Employee_Record *************************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employee/" + empID);
    }

    // Below Test methods are for validation

    @Test
    void checkResponseBody() {

        logger.info("****************** Checking Response Body *************************");
        String responsebody = response.getBody().asString();
        logger.info("Response body ==> " + responsebody);
        Assert.assertEquals(responsebody.contains(empID), true);
    }

    @Test
    void checkStatusCode(){

        logger.info("****************** Checking Status Code *************************");
        int statuscode=response.getStatusCode();
        logger.info("Status code ==> "+ statuscode);
        Assert.assertEquals(statuscode,200);
    }

    @Test
    void checkResponseTime(){

        logger.info("****************** Checking Response Time *************************");
        long responsetime=response.getTime();
        logger.info("Response Time is ==> "+ responsetime);

        if(responsetime>2000)
            logger.warn("Response time is greater than 7000'");

        Assert.assertTrue(responsetime<7000);

    }

    @Test
    void checkContentType(){
        String contentType= response.header("Content-Type");
        Assert.assertEquals(contentType,"text/html; charset=UTF-8");
    }

    @Test
    void checkServerType(){
        String serverType=response.header("Server");
        Assert.assertEquals(serverType,"nginx/1.16.0");
    }

    @Test
    void checkContentLength(){
        String contentLength=response.header("Content-Length");
        logger.info("Content Length is ==> "+ contentLength);
        Assert.assertTrue(Integer.parseInt(contentLength)<1500);
    }

    @AfterClass
    void tearDown(){

        logger.info("****************** Finished TC002_Get_Single_Employee_Record *************************");
    }


}
