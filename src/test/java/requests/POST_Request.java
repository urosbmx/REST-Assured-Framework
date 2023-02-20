package requests;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class POST_Request {
    public static void postRequest(String url, String path, int statusCode, String body){
            RestAssured.baseURI=url;
            given().log().all()
                    .header("Content-Type","application/json")
                    .body(body)
                    .when()
                    .get(path)
                    .then()
                    .assertThat()
                    .log()
                    .all()
                    .statusCode(statusCode);
    }
}
