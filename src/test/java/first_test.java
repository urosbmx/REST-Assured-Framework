import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class first_test {
    String body ="{\n" +
            "  \"location\": {\n" +
            "    \"lat\": -38.383494,\n" +
            "    \"lng\": 33.427362\n" +
            "  },\n" +
            "  \"accuracy\": 50,\n" +
            "  \"name\": \"Frontline house\",\n" +
            "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
            "  \"address\": \"29, side layout, cohen 09\",\n" +
            "  \"types\": [\n" +
            "    \"shoe park\",\n" +
            "    \"shop\"\n" +
            "  ],\n" +
            "  \"website\": \"http://google.com\",\n" +
            "  \"language\": \"French-IN\"\n" +
            "}";

    @Test
    public void TestCase01(){
        //given - all input datails
        //When  - Submit the API
        //then - Valid response
        RestAssured.baseURI = "http://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(body)
                .when().post("maps/api/place/add/json")
                .then().log().all()
                .assertThat()
                .statusCode(200);

    }
}
