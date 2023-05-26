package tests.Google_Maps_POJO;

import POJO_Class.Location;
import POJO_Class.addPlace;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class first_test {

    @Test
    public void testCase(){

        List<String> myList = new ArrayList<String>();
        addPlace  p = new addPlace();
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


        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().queryParam("key","=qaclick123")
                .header("Content-Type", "application/json")
                .log()
                .all()
                .body(p)
                .post("/maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }
}
