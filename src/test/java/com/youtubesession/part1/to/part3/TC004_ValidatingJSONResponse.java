package com.youtubesession.part1.to.part3;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_ValidatingJSONResponse {

    @Test
    public void getWeatherDetails(){

        // Specify base URI

        RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";

        // Request object , this will create request to the server

        RequestSpecification httpRequest=RestAssured.given();

        // Response Object
        // this response output includes response body,status code, status line and headers
        Response response=httpRequest.request(Method.GET,"/Delhi");

        // print response body only in console window
        String responseBody=response.getBody().asString();
        System.out.println("Response body is :"+responseBody);

        // verify response body , here verifying only one parameter
        Assert.assertEquals(responseBody.contains("Delhi"),true);

        // verify all values from response body
        JsonPath jsonpath=response.jsonPath();
        System.out.println(jsonpath.get("City").toString());
        System.out.println(jsonpath.get("Temperature").toString());
        System.out.println(jsonpath.get("Humidity").toString());
        System.out.println(jsonpath.get("WeatherDescription").toString());
        System.out.println(jsonpath.get("WindSpeed").toString());
        System.out.println(jsonpath.get("WindDirectionDegree").toString());

        Assert.assertEquals(jsonpath.get("Temperature"),"20 Degree celsius");
    }
}
