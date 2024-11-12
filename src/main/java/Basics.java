import files.payload;
import files.reusablemethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args) {

        /**
         * Validate Add Place API is working as expected
         * //given - all input details
         * when - submit the API - resource,http method
         * then - Validate the response
         */

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.Addplace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();

        System.out.println("The response of the step is: ");
        System.out.println(response);

        JsonPath js = new JsonPath(response); //for parsing json
        String placeid = js.getString("place_id");

        System.out.println(placeid);

        /**
         * Add place -> Update place with new address -> Get place to validate if new address is present in response
         */
        String address = "70 winter walk, USA";

        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeid+"\",\n" +
                        "\"address\":\""+address+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        String getplaceresponse= given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath getaddress = reusablemethods.rawtojson(getplaceresponse);
        String extractedaddress =getaddress.getString("address");
        System.out.println(extractedaddress);
        System.out.println(address);

        Assert.assertEquals(extractedaddress,address,"The assertion failed");


    }
}
