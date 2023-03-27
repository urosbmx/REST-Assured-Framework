package tests.fake_google_maps_api;

import files.payload;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import funcionality.createPlace;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class create_place {

    String placeID = createPlace.createPlaceCall();
    @Test
    public void TestCase01(){
        //given - all input datails
        //When  - Submit the API
        //then - Valid response
        RestAssured.baseURI = "http://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.addPlace())
                .when()
                .post("maps/api/place/add/json")
                .then().log().all()
                .assertThat()
                .statusCode(200);

    }
    @Test
    public  void TestCase02(){
        RestAssured.baseURI = "http://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all()
                .assertThat()
                .body("scope",equalTo("APP"));
    }

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

    @Test
    public void TestCase04(){
        RestAssured.baseURI="http://rahulshettyacademy.com";
        given()
                .log()
                .all()
                .queryParam("place_id",placeID)
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .body("address",equalTo("Radnicka 71a"));
    }


}
