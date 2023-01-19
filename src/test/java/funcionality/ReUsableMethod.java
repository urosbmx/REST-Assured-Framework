package funcionality;

import io.restassured.path.json.JsonPath;

public class ReUsableMethod {
    public static JsonPath JsonConvert(String ressponses){
        return new JsonPath(ressponses);
    }
}
