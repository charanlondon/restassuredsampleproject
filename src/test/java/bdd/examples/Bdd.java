package bdd.examples;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Bdd {

    // Specify base URI

    RestAssured.baseURI="http://restapi.demoqa.com/customer";
    // Request object , this will create request to the server
    RequestSpecification httpRequest=RestAssured.given();
    // post request payload
    JSONObject requestParameters= new JSONObject();
        requestParameters.put("FirstName","Charan12345");
        requestParameters.put("LastName","XYZ12345");
        requestParameters.put("UserName","Charan12345");
        requestParameters.put("Password","test12345");
        requestParameters.put("Email","charan123@gmail.com");
    // below method is to add above mentioned json parameters as body
        httpRequest.body(requestParameters.toJSONString());
        Response response= fireOffer(requestParameters.toJSONString(),200);

        //validation from response payload using json path finder
        JsonPath jsonObj=response.jsonPath(); // this wlll get all json path of response payload
        String empName= jsonObj.get("name");
        String eage= jsonObj.get("age");
        String edob= jsonObj.get("dob");
        Assert.assertEquals(empName,"charan");
        Assert.assertEquals(eage,32);
        Assert.assertEquals(edob,"11081948");


    public Response fireOffer(String myRequest, int status) {
        RestAssured.useRelaxedHTTPSValidation();
       // RestAssured.baseURI = "http://" + propertyValues.getSalsaHost();
        //System.out.println("SALSA HOST = " + propertyValues.getSalsaHost());
        return
                given()
                        .request().body(myRequest).headers("Content-Type", "application/json", "Accept-Encoding", "gzip,deflate").
                        when()
//                        .post(propertyValues.getURIPath("loansOffer")).
                        .post("URL").
                        then()
                        .assertThat()
                        .statusCode(status).extract().response();
    }


    public Response fireApiService(String myRequest, String serviceName, int status) {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "http://" + propertyValues.getSalsaHost();
        System.out.println("SALSA HOST = " + propertyValues.getSalsaHost());
        System.out.println("SALSA HOST2 = " + propertyValues.getURIPath("loansPFSA"));

        Response response=null;
        switch(serviceName)
        {
            case "OFFER" : {
                response= given()
                        .request().body(myRequest).headers("SOAPAction", "http://www.lloydstsb.com/Schema/Enterprise/offerProductArrangement", "Content-Type", "text/xml;charset=UTF-8", "Accept-Encoding", "gzip,deflate", "Connection", "Keep-Alive", "User-Agent", "Apache-HttpClient/4.1.1").
                                when()
                        .post(propertyValues.getURIPath("loansOffer")).
                                then()
                        .assertThat()
                        .statusCode(status).extract().response();
                break ;
            }

            case "PMLA" : {
                response= given()
                        // .request().body(myRequest).headers("SOAPAction", "http://www.lloydstsb.com/Schema/Enterprise/prepareMaximumLendingamount", "Content-Type", "text/xml;charset=UTF-8", "Accept-Encoding", "gzip,deflate", "Connection", "Keep-Alive", "User-Agent", "Apache-HttpClient/4.1.1").
                        .request().body(myRequest).
                                when()
                        .post(propertyValues.getURIPath("loansPMLA")).
                                then()
                        .assertThat()
                        .statusCode(status).extract().response();
                break ;
            }

            case "PFSA" : {

                response= given()
                        .request().body(myRequest).
                                when()
                        .post(propertyValues.getURIPath("loansPFSA")).
                                then()
                        .assertThat()
                        .statusCode(status).extract().response();
                break ;
            }

            case "PRD" : {

                response= given()
                        .request().body(myRequest).
                                when()
                        .post(propertyValues.getURIPath("loansPRD")).
                                then()
                        .assertThat()
                        .statusCode(status).extract().response();
                break ;
            }

            case "ACTIVATE" : {

                response= given().contentType("application/json")
                        .request().body(myRequest).
                                when()
                        .post(propertyValues.getURIPath("loansActivate")).
                                then()
                        .assertThat()
                        .statusCode(status).extract().response();
                break ;
            }
            case "SIRA" : {

                response= given().contentType("application/json")
                        .request().body(myRequest).
                                when()
                        .post(propertyValues.getURIPath("loansSIRA")).
                                then()
                        .assertThat()
                        .statusCode(status).extract().response();
                break ;
            }


        }

        return response;
    }
}
