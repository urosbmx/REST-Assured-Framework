package tests.fake_google_maps_api;

import POJO_Class.ErrorMessages;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.expect;

public class get_data_for_place_POJO {

    ErrorMessages m = new ErrorMessages();
    @Test
    public  void get_data(){

        RestAssured.baseURI = "http://rahulshettyacademy.com";
       ErrorMessages er  = expect()
                .defaultParser(Parser.JSON)
                .given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .queryParam("place_id","6f15c045ab33c53d4f3fa4a458ddeefa")
                .get("/maps/api/place/get/json?key=qaclick123").as(ErrorMessages.class);
                if(er.getMsg().equals("Get operation failed, looks like place_id  doesn't exists")){
                    Assert.assertEquals(er.getMsg(),"Get operation failed, looks like place_id  doesn't exists","This test is pass");
                }else {
                    Assert.fail();
                }

    }

}
