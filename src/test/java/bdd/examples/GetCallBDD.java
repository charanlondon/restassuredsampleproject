package bdd.examples;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

// we can't write given() when() then() because we have to import 2 static methods manually
// for RestAssured BDD , hamcrest library is compulsory you have to import.

public class GetCallBDD {

    @Test
    public void test_numberOfCircuitsFor2017_Season(){

//        given().
//        when().
//        then().
//        assert()

        given().
                when().get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat(). // assert() will validate the response
                statusCode(200). // validating status code
                // validating number of circuits from body
                body("MRData.CircuitTable.Circuits",hasSize(20)). // Matchers class having Size method
                header("Content-Length",equalTo("4551")); // validating response headers



    }
}
