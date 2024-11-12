import files.payload;
import io.restassured.path.json.JsonPath;

public class Complexjasonparse {


    public static void main(String[] args) {

        JsonPath js = new JsonPath(payload.Courseprice());

        /**
         * Print No of courses returned by API
         */

      Integer count = js.getInt("courses.size()");
        System.out.println(count);

        /**
         * Print Purchase Amount
         */
        Integer purchaseamount =js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseamount);

        /**
         * Print Title of the first course
         */


        String course= js.getString("courses[0].title");
        System.out.println(course);

        /**
         * Print All course titles and their respective Prices
         */

        for ( int i=0 ; i<3 ; i++){

            String coursename= js.getString("courses["+i+"].title");
            System.out.println(coursename);
            Integer price = js.getInt("courses["+i+"].price");
            System.out.println(price);


        }

        System.out.println("Print no of copies sold by RPA Course");

        for(int i=0;i<count;i++)
        {
            String courseTitles=js.get("courses["+i+"].title");
            if(courseTitles.equalsIgnoreCase("RPA"))
            {
                int copies=js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }


        }


    }



}
