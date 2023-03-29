package requests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.urlEncodingEnabled;

public class POST_Request {
    public static void POSTRequest(String URL, String header, String queryParametar, String queryValue, String body, String URL_Sufix, Boolean extraStep, int expectStatusCode,String parametarResponse, String expactValue){
        urlEncodingEnabled = false;
        Response response =null;
        try {
            if(!queryParametar.equals("")){
                response = expect()
                        .given()
                        .baseUri(URL)
                        .header("Content-Type", header)
                        .queryParam(queryParametar, queryValue)
                        .body(body)
                        .log()
                        .all()
                        .post(URL_Sufix)
                        .then()
                        .log()
                        .all()
                        .extract()
                        .response();

                int responseCode = response.statusCode();
                if(extraStep){

                    if(responseCode == expectStatusCode ){

                        if(!parametarResponse.equals("")){
                            JsonPath jsonPath= response.jsonPath();
                            String parametar = jsonPath.get(parametarResponse);


                            if(parametar.equals(expactValue)){
                                Assert.assertEquals(parametar,expactValue);
                            }
                            else{
                                Assert.fail("Wron response");
                            }
                        }

                    }else{
                        Assert.fail("Wrong status code expected"+ expectStatusCode + " received" + responseCode);
                    }
                }

            }else {
                response = expect()
                        .given()
                        .baseUri(URL)
                        .header("Content-Type", header)
                        .body(body)
                        .log()
                        .all()
                        .post(URL_Sufix)
                        .then()
                        .log()
                        .all()
                        .extract()
                        .response();

                int responseCode = response.statusCode();
                if(extraStep){

                    if(responseCode == expectStatusCode ){

                        if(!parametarResponse.equals("")){
                            JsonPath jsonPath= response.jsonPath();
                            String parametar = jsonPath.get(parametarResponse);


                            if(parametar.equals(expactValue)){
                                Assert.assertEquals(parametar,expactValue);
                            }
                            else{
                                Assert.fail("Wron response");
                            }
                        }

                    }else{
                        Assert.fail("Wrong status code expected"+ expectStatusCode + " received" + responseCode);
                    }
                }
            }




        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
