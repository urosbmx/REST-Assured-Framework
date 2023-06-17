package tests.request_builder;

import POJO_Class.Location;
import POJO_Class.addPlace;
import funcionality.createPlace;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class TC_01_create_place_data {

    String placeID = createPlace.createPlaceCall();
    @Test
    public void TCForSchema(){

        List<String> myList = new ArrayList<String>();
        addPlace p = new addPlace();
        Location l = new Location();
        p.setAccuracy(51);
        p.setAddress("Dzona Kenedija 23");
        p.setName("Panetal Palacinke");
        p.setPhone_number("+381/5602-1-23");
        p.setWebsite("http://pantela-palacinke.com");
        p.setLanguage("English");
        myList.add("Novi Beograd");
        myList.add("Cukaricka Padina");
        p.setTypes(myList);
        l.setLat(-21.2211);
        l.setLng(54.021);
        p.setLocation(l);

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();

        given()
                .spec(req)
                .log()
                .all()
                .body(p)
                .post("/maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

    }
}
