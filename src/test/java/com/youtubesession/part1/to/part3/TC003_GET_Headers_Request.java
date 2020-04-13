package com.youtubesession.part1.to.part3;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_GET_Headers_Request {

// below test is to get header and validation

    @Test
    public void getGoogleMap(){

        // Specify base URI

        RestAssured.baseURI="http://maps.googleapis.com";

        // Request object , this will create request to the server

        RequestSpecification httpRequest=RestAssured.given();

        // Response Object
        // this response output includes response body,status code, status line and headers
        Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmj2HoELb4jyls");

        // print response body only in console window
        String responseBody=response.getBody().asString();
        System.out.println("Response body is :"+responseBody);

       // Validating headers
        String contentType=response.header("Content-Type"); //capture details of Content-Type header
        System.out.println("content type is :"+contentType);
        Assert.assertEquals(contentType,"application/xml; charset=UTF-8");

        String contentEncoding=response.header("Content-Encoding"); //capture details of Content-Encoding header
        System.out.println("content Encoding is :"+contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");

        // capture all the headers from response
        Headers allheaders=response.headers();

        for(Header header: allheaders)
        {
            System.out.println(header.getName() + "     "+header.getValue());
        }

    }
}
