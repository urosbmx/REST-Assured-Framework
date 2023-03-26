import com.github.javafaker.Faker;
import funcionality.ReUsableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class check_book_value {

    String baseURL ="http://216.10.245.166/";
    String addBookUrl = "Library/Addbook.php";
    Faker faker = new Faker();
    String name = faker.name().firstName();
    Random random = new Random();
    int rNumber = random.nextInt(180);
    String dinamicPayLoad ="{\n" +
            "\n" +
            "\"name\":\"Learn Appium Automation with "+name+"\",\n" +
            "\"isbn\":\"\",\n" +
            "\"aisle\":\""+rNumber+"\",\n" +
            "\"author\":\"John foe\"\n" +
            "}\n";
@Test
    public void check_book_name(){
    RestAssured.baseURI = baseURL;
    String response = given().log().all()
            .header("Content-Type","application/json")
            .body(dinamicPayLoad)
            .when().post(addBookUrl)
            .then().log().all()
            .assertThat()
            .extract().response().asString();

    JsonPath js = ReUsableMethod.JsonConvert(response);
    String ID =  js.get("ID");


    given().log().all()
            .header("Content-Type","application/json")
            .when().get("Library/GetBook.php?ID="+ID)
            .then().log().all()
            .assertThat()
            .statusCode(200);




    }

@Test
    public void check_missing_book(){
    given().log().all()
            .header("Content-Type","application/json")
            .when().get("Library/GetBook.php?ID=2")
            .then().log().all()
            .assertThat()
            .statusCode(200)
            .body("book_name",contains("Learn Appium Automation with Colton"));




    }
}
