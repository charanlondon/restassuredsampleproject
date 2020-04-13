package com.youtubesession.part1.to.part3;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_GET_Request {

    // Below is get json body, status code and status line from API response
    //Get Weather conditions - 1st API script using automation
    @Test
    public void getWeatherDetails(){

        // Specify base URI

        RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";

        // Request object , this will create request to the server

        RequestSpecification httpRequest=RestAssured.given();

        // Response Object , response object holds the data
        // this response output includes response body,status code, status line and headers

        Response response=httpRequest.request(Method.GET,"/Hyderabad");

        // print response body only in console window
        String responseBody=response.getBody().asString();
        System.out.println("Response body is :"+responseBody);

        // print status code and status line in console window
        int statusCode=response.getStatusCode();
        System.out.println("status code is :"+statusCode);
        // status code Validation
        Assert.assertEquals(statusCode,200);
        String statusLine=response.getStatusLine();
        System.out.println("status line is :"+statusLine);
        //status line validation
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

    }
}
