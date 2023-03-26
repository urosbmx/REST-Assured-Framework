package funcionality;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.testng.Assert;

import static io.restassured.RestAssured.expect;


public class CommonFunctionality {

//    GET
    public static Response GET_Request(String baseURL,String path,String qveryParam,int expectStatusCode, String expectResponse,boolean asertStep){
        Response response = null;
        RestAssured.baseURI = baseURL;
        try{
            response = (Response) expect()
                    .given()
                    .log()
                    .all()
                    .contentType("application/json")
                    .when().get(path+qveryParam)
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();

            int responseCode = response.statusCode();
            String responseBody = response.getBody().prettyPrint();

            if(asertStep){
                if (responseCode == expectStatusCode){
                    Assert.assertEquals(responseCode,expectStatusCode);
                }else {
                    Assert.fail();
                }
                if(responseBody.contains(expectResponse)){
                    System.out.println("Response body is " + responseBody);
                }else {
                    Assert.fail();
                }
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        return response;
    }

}
