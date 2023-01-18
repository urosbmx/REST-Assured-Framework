package funcionality;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class createPlace {
     public static String createPlaceCall(){
         RestAssured.baseURI="http://rahulshettyacademy.com";
         String resposne = given()
                 .queryParam("key","qaclick123")
                 .header("Content-Type","application/json")
                 .body(payload.addPlace())
                 .when().post("maps/api/place/add/json")
                 .then().extract().response().asString();
         JsonPath js = new JsonPath(resposne);
         return js.getString("place_id");
     }
}
