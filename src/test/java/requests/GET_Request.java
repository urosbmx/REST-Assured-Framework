package requests;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class GET_Request {
    public static Response GETRequest(String baseURL, String header, String URL_Sufix, String queryParametar, String queryValue, boolean extraStep, int expectStatusCode, JsonNode expactJSON) {
        urlEncodingEnabled = false;
        Response response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response = expect()
                    .given()
                    .baseUri(baseURL)
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
            String realResonse = response.asPrettyString();

            if (extraStep) {

                if (responseCode == expectStatusCode) {

                    if(!expactJSON.equals("")){
                        JsonNode node1 = objectMapper.readTree(realResonse);
                        if(node1.equals(expactJSON)){
                            System.out.println("Response body is the same \n"+realResonse);
                        }
                        else {
                            Assert.fail("Wrong respons body");
                        }

                    }
                } else {
                    Assert.fail("Wrong status code expected"+ expectStatusCode + " received" + responseCode);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return response;
    }
}
