package com.youtubesession.part1.to.part3;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_GET_Request_Authentication {


    @Test
    public void authorizationTest(){

        // Specify base URI

        RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";

        // Basic authentication
        PreemptiveBasicAuthScheme authScheme= new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");
        RestAssured.authentication=authScheme;// assigning basic authentication object to RestAssured

        // Request object , this will create request to the server

        RequestSpecification httpRequest=RestAssured.given();

        // Response Object
        // this response output includes response body,status code, status line and headers

        Response response=httpRequest.request(Method.GET,"/");// slash("/") represents to homepage because we are not adding anything

        // print response body only in console window
        String responseBody=response.getBody().asString();
        System.out.println("Response body is :"+responseBody);

        // print status code  console window
        int statusCode=response.getStatusCode();
        System.out.println("status code is :"+statusCode);
        Assert.assertEquals(statusCode,200);
    }
}


