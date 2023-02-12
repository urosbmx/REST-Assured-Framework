import com.beust.jcommander.Parameter;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import  files.payload;

public class BookStore {


    @Parameter
    String baseURL ="http://216.10.245.166/";
    String addBookUrl = "Library/Addbook.php";
    Faker faker = new Faker();
    String name = faker.name().firstName();
    Random random = new Random();
    int rNumber = random.nextInt(180);
    String dinamicPayLoad ="{\n" +
            "\n" +
            "\"name\":\"Learn Appium Automation with Java"+name+"\",\n" +
            "\"isbn\":\"\",\n" +
            "\"aisle\":\""+rNumber+"\",\n" +
            "\"author\":\"John foe\"\n" +
            "}\n";


    @Test
 public void BookStore_AddBook(){
        RestAssured.baseURI = baseURL;
        given().log().all()
                .header("Content-Type","application/json")
                .body(payload.addBook())
                .when().post(addBookUrl)
                .then().log().all()
                .assertThat()
                .statusCode(404);



 }
 @Test

 public void BookStore_Check_message(){
        RestAssured.baseURI = baseURL;
        given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.addBook())
                .when().post(addBookUrl)
                .then().log().all()
                .assertThat()
                .body("msg",equalTo("Add Book operation failed, looks like the book already exists"));

 }
 @Test

 public  void  BookStore_happyPath(){
        RestAssured.baseURI=baseURL;
        given().log().all()
                .header("Content-Type", "application/json")
                .body(dinamicPayLoad)
                .when().post(addBookUrl)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}
