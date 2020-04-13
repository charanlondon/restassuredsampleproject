package commonn.employeeapi.testcases;

import commm.empoyeeapi.utiliities.RestUtils;
import common.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// This test case is for update an existing employee record
// PUT & POST method are similar , POST method we are using to create new record but PUT method we using for update existing record

public class TC004_Put_Employee_Record extends TestBase {

    // RestUtils class contains java methods to create random data, no need to hard code the employee data
    String empName= RestUtils.empName();
    String empSalary=RestUtils.empSal();
    String empAge=RestUtils.empAge();

    @BeforeClass
    void putEmployeeRecord() throws InterruptedException {

        logger.info("****************** Started TC004_PutEmployee_Record *************************");
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        // creating body of json for post request
        //the above utility file will create the dynamic data so no need to hard code the employee name , employee salary
        // and employee age, we are using RestUtils class because this will create random data
        JSONObject requestParameters= new JSONObject();
        requestParameters.put("name",empName);
        requestParameters.put("salary",empSalary);
        requestParameters.put("age",empAge);

        // sending this in json format so that we need to add header
        httpRequest.header("Content-Type","application/json");

        // below method is to add above mentioned json parameters as body
        httpRequest.body(requestParameters.toJSONString());

        // finally sending post request
        // Response Object
        Response response=httpRequest.request(Method.PUT,"/update"+empID);// pass the empID,based on emp id it will update record
        Thread.sleep(5000);
    }

    // Below Test methods are for validation

    @Test
    void checkResponseBody() {

        logger.info("****************** Checking Response Body *************************");
        String responsebody = response.getBody().asString();
        logger.info("Response body ==> " + responsebody);
        Assert.assertEquals(responsebody.contains(empName), true);
        Assert.assertEquals(responsebody.contains(empSalary), true);
        Assert.assertEquals(responsebody.contains(empAge), true);
    }

    @Test
    void checkStatusCode(){

        logger.info("****************** Checking Status Code *************************");
        int statuscode=response.getStatusCode();
        logger.info("Status code ==> "+ statuscode);
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

        logger.info("****************** Finished TC004_Put_Employee_Record *************************");
    }
}
