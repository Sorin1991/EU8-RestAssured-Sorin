package cydeo.day4;

import cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .get("/countries");

        //get the second country name with JsonPath

        //to use jsonpath we assign response to JsonPath

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        // items.country_id
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionsId = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println(countryNameWithRegionsId);
    }

    @DisplayName("GET request /employees wih query param")
    @Test
    public void test2(){
        //we added limit query param to get 107 employees
       Response response =  given().queryParam("limit",107)
                                .when().get("/employees");

       //get me all email of empoloyees who is working as IT_PROG
        JsonPath jsonPath =response.jsonPath();

        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==/IT_PROG/}.email");
        System.out.println(employeeITProgs);

        //get me first name of employees who is making more than 10000
        List<String> empNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(empNames);

        //get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String kingNameWithPatMethod = response.path("items.max {it.salary}.first_name");

        System.out.println("kingFirstName = " + kingFirstName);

        System.out.println("kingNameWithPatMethod = " + kingNameWithPatMethod);


    }

}
