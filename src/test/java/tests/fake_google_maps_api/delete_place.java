package tests.fake_google_maps_api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import funcionality.createPlace;
public class delete_place {
    String PlaceID =  createPlace.createPlaceCall();
    @Test
    public void testCase01DeleteJustCreatedPlace(){
        RestAssured.baseURI="https://rahulshettyacademy.com";

        given()
                .log()
                .all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"place_id\":\""+PlaceID+"\"\n" +
                        "}\n")
                .when()
                .delete("maps/api/place/delete/json")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("status", equalTo("OK"));

    }

    @Test
    public void testCase02DeleteNotExistingPlaceID(){
        given()
                .log()
                .all()
                .queryParam("key")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"place_id\":\"6f15c045ab33c53d4f3fa4a458ddeefa\"\n" +
                        "}\n")
                .when()
                .delete("maps/api/place/delete/json")
                .then()
                .log()
                .all()
                .statusCode(404)
                .body("msg",equalTo("Delete operation failed, looks like the data doesn't exists"));
    }
}
