import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import funcionality.createPlace;
import  funcionality.ReUsableMethod;
import io.restassured.filter.log.RequestLoggingFilter;
public class get_data_for_place {
    String placeID = createPlace.createPlaceCall();

       @Test
        public  void TestCase01_Get_Data_for_Place(){
           RestAssured.baseURI="http://rahulshettyacademy.com";
           given().log().all()
                   .queryParam("place_id","6f15c045ab33c53d4f3fa4a458ddeefa")
                   .queryParam("key","qaclick123")
                   .header("Content-Type","application/json")
                   .when().get("/maps/api/place/get/json")
                   .then()
                   .log()
                   .all()
                   .assertThat()
                   .statusCode(404)
                   .body("msg",equalTo("Get operation failed, looks like place_id  doesn't exists"));

        }
        @Test
    public  void TestCase02_With_Corect_PlaceID(){

        RestAssured.baseURI="http://rahulshettyacademy.com";
//        given().log().all()
//                .queryParam("place_id","2175b1ccb05886762d0ce7e8b140c51c")
//                .queryParam("key","qaclick123")
//                .header("Content-Type","application/json")
//                .when().get("/maps/api/place/get/json")
//                .then()
//                .log()
//                .all()
//                .statusCode(200);
//How to get respons from API
        String getJsonBody =
                given()
                        .log()
                        .all()
                        .queryParam("place_id","2175b1ccb05886762d0ce7e8b140c51c")
                        .queryParam("key","qaclick123")
                        .when()
                        .get("/maps/api/place/get/json")
                        .then()
                        .extract()
                        .response()
                        .asString();

        String test =ReUsableMethod.JsonConvert(getJsonBody).getString("location.longitude");
            System.out.println(test);



    }

    @Test

    public void testCase03_PlaceID_New(){
        given().log()
                .all()
                .queryParam("place_id",placeID)
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .when().get("/maps/api/place/get/json")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("location.latitude", equalTo("-38.383494"));
    }



}
