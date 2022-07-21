package cydeo.day4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inisde this variable so that we dont need to type each http method
        baseURI = "http://api.cybertektraining.com";
    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as a path parameter
        //verify status code=200 /content type=application/jso;charset=UTF -8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that

        /*

            firstName Vera
            batch 14
            section 12
            emailAddress aaa@gmail.com
            companyName Cybertek
            state IL
            zipCode 60606

            using JsonPath
         */
    }

}
