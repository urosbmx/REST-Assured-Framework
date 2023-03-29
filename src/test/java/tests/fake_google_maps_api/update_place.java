package tests.fake_google_maps_api;

import funcionality.createPlace;
import io.restassured.RestAssured;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class update_place {
    String placeID = createPlace.createPlaceCall();
    @Test
    public void  TestCase03(){

        RestAssured.baseURI="http://rahulshettyacademy.com";
        given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\"Radnicka 71a\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200);

    }
}
