package tests.test_for_books;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import  funcionality.ReUsableMethod;

import  files.payload;



public class add_book_store {

    String baseURL ="http://216.10.245.166/";
    String addBookUrl = "Library/Addbook.php";
    Faker faker = new Faker();
    String name = faker.name().firstName();
    Random random = new Random();


    @Test()
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
                .body(files.addNewBook.addBooks(name,random))
                 .when().post(addBookUrl)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void BookStore_takeValue(){
        RestAssured.baseURI = baseURL;
        String ressponses = given().log().all()
                .header("Content-Type","application/json")
                .body(files.addNewBook.addBooks(name,random))
                .when().post(addBookUrl)
                .then().log().all()
                .assertThat()
                .statusCode(200).extract().response().asString();
                JsonPath js = ReUsableMethod.JsonConvert(ressponses);
//                String ID = js.get("ID");

    }

}
