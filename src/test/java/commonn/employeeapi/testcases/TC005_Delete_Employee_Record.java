package commonn.employeeapi.testcases;

import common.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
// this test case is to delete existing record based on employee id

public class TC005_Delete_Employee_Record extends TestBase {

    @BeforeClass
    void deleteEmployee() {

        logger.info("****************** Started TC005_Delete_Employee_Record *************************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employees");

        //First get the json path object instance from the Response interface
        JsonPath jsonPathEvaluator=response.jsonPath();

        //Capture the emp id
        String empID=jsonPathEvaluator.get("[0].id"); // taking 1st record of ID value from json response
        response = httpRequest.request(Method.DELETE, "/delete"+empID);
    }

    // Below Test methods are for validation

    @Test
    void checkResponseBody() {

        logger.info("****************** Checking Response Body *************************");
        String responsebody = response.getBody().asString();
        Assert.assertEquals(responsebody.contains("successfully! deleted Records"), true);
    }

    @Test
    void checkStatusCode(){

        logger.info("****************** Checking Status Code *************************");
        int statuscode=response.getStatusCode();
        Assert.assertEquals(statuscode,200);
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

        logger.info("****************** Finished TC005_Delete_Employee_Record *************************");
    }
}
