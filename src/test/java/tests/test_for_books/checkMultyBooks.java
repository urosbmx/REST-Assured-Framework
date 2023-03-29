package tests.test_for_books;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static requests.GET_Request.GETRequest;


import java.util.Random;

public class checkMultyBooks {
    String baseURL ="http://216.10.245.166/";
    String  checkBookUrl = "Library/GetBook.php" ;



@DataProvider(name = "CorectIDBooks")
    public Object[]idData(){
        return new Object[] {"45","21","32","22","11","13","45"};
    }

@DataProvider(name = "incorrect_Books")
public Object[]incorrectID(){
    return new Object[] {"4221","3321","0022","3123"};
}

    @Test(dataProvider = "CorectIDBooks")
    public void checkBookID(String ID){
        GETRequest(baseURL,"application/json",checkBookUrl,"ID",ID,true,200,"","");
    }
    @Test(dataProvider = "incorrect_Books")
    public void incorrect_Books(String ID){

        GETRequest(baseURL,"application/json",checkBookUrl,"ID",ID,true,404,"msg","The book by requested bookid / author name does not exists!");
    }


}
