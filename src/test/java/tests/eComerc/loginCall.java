package tests.eComerc;

import POJO_Class.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import resources.env_files;

import static io.restassured.RestAssured.given;

public class loginCall {
    public static String callForLogin() {
        String pass = "Iamking@000";
        RequestSpecification req =	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();
        Map<String,String> qvery = new HashMap<>();
        qvery.put("userEmail",  "rahulshetty@gmail.com");
        qvery.put("userPassword","Iamking@000");
        String mapAssString = StringUtils.join(qvery.entrySet(),"&");
        RequestSpecification reqLogin = given().spec(req).body(qvery);
        String logins = reqLogin.when().post("/api/ecom/auth/login").then().extract().response().asString();
        return logins;
    }


}
