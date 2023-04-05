package tests.fake_google_maps_api;
import org.testng.annotations.Test;
import funcionality.createPlace;


import static requests.GET_Request.GETRequest;

public class get_data_for_place {
    String URL = "http://rahulshettyacademy.com";
    String path="/maps/api/place/get/json?key=qaclick123";
    String placeID = createPlace.createPlaceCall();

    @Test
    public void TestCase01_Get_Data_for_Place() {

        GETRequest(URL, "application/json", path, "place_id", "6f15c045ab33c53d4f3fa4a458ddeefa", true, 404, "msg", "Get operation failed, looks like place_id  doesn't exists");

    }


    @Test
    public void TestCase02_Check_Name() {
        GETRequest(URL, "application/json", path, "place_id", placeID, true, 200, "name", "Frontline house");

    }

    @Test

    public void testCase03_PlaceID_New() {

        GETRequest(URL, "application/json", path, "place_id", placeID, true, 200, "location.latitude", "-38.383494");
    }

    @Test

    public void testCase04_Check_address() {

        GETRequest(URL, "application/json", path, "place_id", placeID, true, 200, "address", "29, side layout, cohen 09");
    }
    @Test
    public void testCase05_Check_types() {

        GETRequest(URL, "application/json", path, "place_id", placeID, true, 200, "types", "shoe park,shop");
    }

    @Test
    public void testCase06_Check_language() {

        GETRequest(URL, "application/json", path, "place_id", placeID, true, 200, "language", "French-IN");
    }

    @Test
    public void testCase07_Check_website() {

        GETRequest(URL, "application/json", path, "place_id", placeID, true, 200, "website", "http://google.com");
    }

}
