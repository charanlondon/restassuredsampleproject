package com.youtubesession.part1.to.part3;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_POST_Request {

// Below test is post the json body and validate the json response and status code

    @Test
    public void registrationSuccessful(){

        // Specify base URI

        RestAssured.baseURI="http://restapi.demoqa.com/customer";

        // Request object , this will create request to the server

        RequestSpecification httpRequest=RestAssured.given();

        // Response Object
        // this is a POST request we need to send some data i.e body or request parameters to server
        // sending this request parameters as json body , using put method to add key and value pair
        // request payload sending along with POST request , since POST request needs body along with parameters

        JSONObject requestParameters= new JSONObject();
        requestParameters.put("FirstName","Charan12345");
        requestParameters.put("LastName","XYZ12345");
        requestParameters.put("UserName","Charan12345");
        requestParameters.put("Password","test12345");
        requestParameters.put("Email","charan123@gmail.com");

        // sending this in json format so that we need to add header
        httpRequest.header("Content-Type","application/json");

        // below method is to add above mentioned json parameters as body
        httpRequest.body(requestParameters.toJSONString());

        // finally sending post request
        // Response Object
        Response response=httpRequest.request(Method.POST,"/register");

        // print response body only in console window
        String responseBody=response.getBody().asString();
        System.out.println("Response body is :"+responseBody);


        // print status code in console window
        int statusCode=response.getStatusCode();
        System.out.println("status code is :"+statusCode);
        // status code Validation
        Assert.assertEquals(statusCode,201);

        // inside json response validate the value of success code using jsonPath() method
        String successCode=response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode,"OPERATION_SUCCESS");

    }
}
