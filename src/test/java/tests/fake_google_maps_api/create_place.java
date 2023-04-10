package tests.fake_google_maps_api;
import files.payload;
import org.testng.annotations.Test;
import static requests.POST_Request.POSTRequest;


public class create_place   {


    String URL = "http://rahulshettyacademy.com";
    @Test
    public void TestCase01(){;

        POSTRequest(URL,"application/json","key","qaclick123", payload.addPlace(),"maps/api/place/add/json",true,200,"status","OK");

    }

    @Test
    public void TestCase02(){;

        POSTRequest(URL,"application/json","key","qaclick123", payload.addPlace(),"maps/api/place/add/json",true,200,"scope","APP");

    }

    @Test
    public void TestCase03(){;

        POSTRequest(URL,"application/json","key","qaclick123", "","maps/api/place/add/json",true,200,"","");

    }



}
