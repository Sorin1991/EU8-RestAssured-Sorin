package cydeo.day6;

import cydeo.pojo.Employee;
import cydeo.pojo.Link;
import cydeo.pojo.Region;
import cydeo.pojo.Regions;
import cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSPojoGetRequestTest extends HRTestBase {

    //create pojo classes for this response
    //then test it with sending get request ot regions endpoint and
    //only pointing first region and converting your pojo

    @Test
    public void regionsTest() {

        JsonPath jsonPath = get("/regions").then().statusCode(200).log().all().extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());


        /*** You can get this part too
         Link link1 = region1.getLinks().get(0);
         System.out.println("link1.getHref() = " + link1.getHref());
         */

    }


    @DisplayName("GET request to / employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet() {

        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        //we are getting error following exception
        //com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "employee_id"

        //after we put annotation in Employee class @JsonIgnoreProperties(ignoreUnknown = true) and will work
        System.out.println(employee1);
    }

    /*
        sed a get request to a regions
        verify that region ids are 1,2,3,4
        verify that regions name Europe,americas,Asia,Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non-used fields
     */

    @DisplayName("Get request to a regions only some fields")
    @Test
    public void regionPojoTest() {
        //send a get request and save everything inside the regions object
        //since we prepare pojo also for all properties we don't need to use any path so as() method is enough

        Regions regions = get("/regions").then().statusCode(200).extract().response().as(Regions.class);

        //verify count is 4
        assertThat(regions.getCount(), is(4));

        //create empty list to store values
        List<String> regionsNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        //get list of region out of regions object
        List<Region> items = regions.getItems();


        //loop through each of region, save their ids and names to empty list that we prepare
        for (Region region : items) {
            regionIds.add(region.getRId());
            regionsNames.add(region.getRegion_name());
        }

        //prepare expected result
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        //compare two result
        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionsNames,is(expectedRegionNames));


    }


}
