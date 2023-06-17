package tests.test_for_books;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import java.util.Random;
import static requests.POST_Request.POSTRequest;
import  files.payload;



public class add_book_store {

    String baseURL ="https://rahulshettyacademy.com/";
    String addBookUrl = "Library/Addbook.php";
    Faker faker = new Faker();
    String name = faker.name().firstName();
    Random random = new Random();


    @Test()
 public void Existing_book(){
        POSTRequest(baseURL,"application/json","","",payload.addBook(),addBookUrl,true,404,"msg","Add Book operation failed, looks like the book already exists");
    }
@Test()
public void BookStore_AddBook_Status(){
    POSTRequest(baseURL,"application/json","","",files.addNewBook.addBooks(name,random),addBookUrl,true,200,"","");
}

    @Test()
    public void BookStore_AddBook_Msg(){
        POSTRequest(baseURL,"application/json","","",files.addNewBook.addBooks(name,random),addBookUrl,true,200,"Msg","successfully added");
    }

    @Test()
    public void BookStore_AddBook_ID(){
        POSTRequest(baseURL,"application/json","","",files.addNewBook.addBooks(name,random),addBookUrl,true,200,"ID","bcd"+random);
    }


}
