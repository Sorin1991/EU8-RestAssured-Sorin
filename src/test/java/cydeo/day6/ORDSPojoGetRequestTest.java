package cydeo.day6;

import cydeo.pojo.Link;
import cydeo.pojo.Region;
import cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSPojoGetRequestTest extends HRTestBase {

    //create pojo classes for this response
    //then test it with sending get request ot regions endpoint and
    //only pointing first region and converting your pojo

    @Test
    public void regionsTest(){

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



}
