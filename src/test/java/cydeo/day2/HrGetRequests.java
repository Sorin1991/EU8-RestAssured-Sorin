package cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    //BeforeAll is a annotation equals to @BeforeClass is testNg, we use with static method name
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://52.91.79.87:1000/ords/hr";
    }

    @DisplayName("Get request to /regions")
    @Test
    public void test1(){
        Response response = get("/regions");

        //print the status code
        System.out.println(response.statusCode());

    }

     /*
        Given accept type is application/json
        When user send get request to /regions/2
        Then response status code mu be 200
        and content type equals to application/json
        and response body contains Americas
         */

    @DisplayName("GET request to /regions/2")
   @Test
    public void test2(){

       Response response =given().accept(ContentType.JSON)
               .when()
                       .get("/regions/2");

       //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json",response.contentType());

        response.prettyPrint();

        //verify body contains Americas
        assertTrue(response.body().asString().contains("Americas"));



   }






}
