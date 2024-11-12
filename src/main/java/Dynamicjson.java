import files.payload;
import files.reusablemethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Dynamicjson {

    @Test(dataProvider = "booksdata")
    public void addbook(String isbn , String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";

        String respose = given().log().all().header("Content-Type", "application/json").body(payload.Addbook(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = reusablemethods.rawtojson(respose);
        String id = js.getString("ID");
        System.out.println(id);

    }

    @DataProvider(name = "booksdata")
    public Object[][] addbookdata() {

        /**
         * Multi Dimensional arrays = collection of arrays
         */

        return new Object[][]{
                {"batman","10"},
                {"ronaldo","07"},
                {"kiara","19"}

        };
    }
}
