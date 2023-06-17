package tests.request_builder;

import POJO_Class.Location;
import POJO_Class.addPlace;
import funcionality.createPlace;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_02_get_place_data {

    String placeID = createPlace.createPlaceCall();
    @Test
    public void TCForSchema(){


        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").addQueryParam("place_id",placeID).setContentType(ContentType.JSON).build();
        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        given()
                .spec(req)
                .log()
                .all()
                .get("/maps/api/place/get/json")
                .then()
                .log()
                .all()
                .assertThat()
                .spec(responseSpec);

    }
}
