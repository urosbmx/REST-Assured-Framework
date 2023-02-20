package requests;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GET_Request {
    public static void getRequest(String url,String path,int extParametar,int statusCode){
            RestAssured.baseURI=url;
            String ext = String.valueOf(extParametar);
            String pathUrl="";
            pathUrl = path+ext;
            given().log().all()
                    .header("Content-Type","application/json")
                    .when()
                    .get(pathUrl)
                    .then()
                    .assertThat()
                    .log()
                    .all()
                    .statusCode(statusCode);
    }
}
