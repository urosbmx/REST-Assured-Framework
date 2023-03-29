package requests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class GET_Request {
    public static Response GETRequest(String baseURL, String header, String URL_Sufix, String queryParametar, String queryValue, boolean extraStep, int expectStatusCode, String parametarResponse, String expactValue) {
        urlEncodingEnabled = false;
        Response response = null;
        try {
            response = expect()
                    .given()
                    .baseUri(baseURL)
                    .header("Content-Type", header)
                    .queryParam(queryParametar, queryValue)
                    .log()
                    .all()
                    .when()
                    .get(URL_Sufix)
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();
            int responseCode = response.statusCode();
            if (extraStep) {

                if (responseCode == expectStatusCode) {

                    if(!parametarResponse.equals("")){
                        JsonPath jsonPathEvaluator = response.jsonPath();
                        String parametar = jsonPathEvaluator.get(parametarResponse);

                        if (parametar.equals(expactValue)) {
                            Assert.assertEquals(parametar, expactValue);

                        } else {
                            Assert.fail("Wrong respons body");
                        }
                    }


                } else {
                    Assert.fail("Wrong status code");
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return response;
    }
}
