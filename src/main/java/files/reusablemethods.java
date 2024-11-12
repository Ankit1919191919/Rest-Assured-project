package files;

import io.restassured.path.json.JsonPath;

public class reusablemethods {

    public static  JsonPath rawtojson(String response){


        JsonPath js = new JsonPath(response); //for parsing json
        return  js;



    }
}
