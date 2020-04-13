package com.youtubesession.part1.to.part3;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Part3_DatadrivenTesting {

// Add new employee , below method is post request with single setup data

    @Test
    public void postNewEmployeesUsingSingleSetupData(){

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";

        RequestSpecification httpRequest=RestAssured.given();

        // need to send json data from request body

        JSONObject requestParms=new JSONObject();

        requestParms.put("name","Smith");
        requestParms.put("salary","7000");
        requestParms.put("age","23");

        // add header type as applicaton/json
        httpRequest.header("Content-Type","application/json");

        // add json to the body of the request
        httpRequest.body(requestParms.toJSONString());

        //  POST request
        Response response=httpRequest.request(Method.POST,"/create");

        // capture response body to perform validations
        String responseBody=response.getBody().asString();
        System.out.println("Response body is :"+responseBody);
        // validation
        Assert.assertEquals(responseBody.contains("Smith"),true);
        Assert.assertEquals(responseBody.contains("7000"),true);
        Assert.assertEquals(responseBody.contains("23"),true);

        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,200);

    }

    // Below method is post request with multiple setup data using DataDriven testng annotations

    @Test(dataProvider = "empDataProvider")
    public void postEmployeesUsingDataProvider(String ename,String esal,String eage){

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";

        RequestSpecification httpRequest=RestAssured.given();

        // need to send json data from request body

        JSONObject requestParms=new JSONObject();

        requestParms.put("name",ename);
        requestParms.put("salary",esal);
        requestParms.put("age",eage);

        // add header type as applicaton/json
        httpRequest.header("Content-Type","application/json");

        // add json to the body of the request
        httpRequest.body(requestParms.toJSONString());

        //  POST request
        Response response=httpRequest.request(Method.POST,"/create");

        // capture response body to perform validations
        String responseBody=response.getBody().asString();
        System.out.println("Response body is :"+responseBody);
        // validation
        Assert.assertEquals(responseBody.contains(ename),true);
        Assert.assertEquals(responseBody.contains(esal),true);
        Assert.assertEquals(responseBody.contains(eage),true);

        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,200);

    }

    @DataProvider(name="empDataProvider")
    Object[] getEmployeeData(){

        String empdata[][]={{"abc","5000","45"},{"def","8900","33"},{"ghi","9000","19"}};
        // we can read the data from Excel instead of hard coded. use excel utility to read data from file
        return empdata;
    }

}
