package commonn.employeeapi.testcases;

import common.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_Get_All_Employees extends TestBase {

    @BeforeClass
    void getAllEmployees(){

        logger.info("****************** Started TC001_Get_All_Employees *************************");
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
        httpRequest=RestAssured.given();
        response=httpRequest.request(Method.GET,"/employees");
    }
// Below Test methods are for validation

    @Test
    void checkResponseBody(){

        logger.info("****************** Checking Response Body *************************");
        String responsebody=response.getBody().asString();
        logger.info("Response body ==> "+ responsebody);
        Assert.assertTrue(responsebody!=null);
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
            logger.warn("Response time is greater than 2000'");

        Assert.assertTrue(responsetime<6000);
    }

    @Test
    void checkContentType(){
       String contentType= response.header("Content-Type");
       Assert.assertEquals(contentType,"application/json;charset=utf-8");
    }

    @Test
    void checkServerType(){
        String serverType=response.header("Server");
        Assert.assertEquals(serverType,"nginx/1.16.0");
    }

    @Test
    void checkContentLength(){
        String contentLength=response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength)<1500);
    }

    @AfterClass
    void tearDown(){

        logger.info("****************** Finished TC001_Get_All_Employees *************************");
    }
}
