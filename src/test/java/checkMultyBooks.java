import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static requests.GET_Request.getRequest;

import java.util.Random;

public class checkMultyBooks {
    String baseURL ="http://216.10.245.166/";
    String addBookUrl = "Library/Addbook.php";
    String  checkBookUrl = "Library/GetBook.php?ID=" ;
    Faker faker = new Faker();
    String name = faker.name().firstName();
    Random random = new Random();
    int rNumber = random.nextInt(180);


@DataProvider(name = "CorectIDBooks")
    public Object[]idData(){
        return new Object[] {45,21,32,22,11,13,45};
    }

@DataProvider(name = "incorrect_Books")
public Object[]incorrectID(){
    return new Object[] {4221,3321,0022,3123};
}

    @Test(dataProvider = "CorectIDBooks")
    public void checkBookID(int ID){
        getRequest(baseURL, checkBookUrl, ID,200);
    }
    @Test(dataProvider = "incorrect_Books")
    public void incorrect_Books(int ID){
    getRequest(baseURL,checkBookUrl,ID,404);
    }

}
