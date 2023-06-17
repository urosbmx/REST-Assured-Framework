package tests.eComerc;
import POJO_Class.OrderDetail;
import funcionality.ReUsableMethod;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import POJO_Class.Orders;

import static io.restassured.RestAssured.*;


public class TC_for_product_form_data {
    String response = loginCall.callForLogin();
    String userID;
    String token;
    String response1;

    @BeforeTest
    public void TC_01_POST_add_product(){
        JsonPath js = ReUsableMethod.JsonConvert(String.valueOf(response));
         userID = js.get("userId");
         token = js.get("token");

          response1 =  given()
                 .baseUri("https://rahulshettyacademy.com/")
                 .header("authorization",token)
                 .header("Accept", "application/json")
                 .header("Content-type", "multipart/form-data")
                 .param("productName","new product")
                 .param("productAddedBy",userID)
                 .param("productCategory", "fashion")
                 .param("productSubCategory","shirts")
                 .param("productPrice","11500")
                 .param("productDescription", "Lenova")
                 .param("productFor", "men")
                 .multiPart("productImage", new File("src/test/java/tests/eComerc/cat.jpg"))
                 .log()
                 .all()
                 .when()
                 .post("api/ecom/product/add-product")
                 .then()
                 .log()
                 .all()
                 .statusCode(201).extract().response().asString();

    }

    @Test
    public void addToOrder(){
        JsonPath resJS = ReUsableMethod.JsonConvert(String.valueOf(response1));
        String productID = resJS.get("productId");

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCountry("India");
        orderDetail.setProductOrderedId(productID);
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        orderDetailList.add(orderDetail);
        Orders orders = new Orders();
        orders.setOrders(orderDetailList);

        given()
                .baseUri("https://rahulshettyacademy.com/")
                .header("authorization",token)
                .header("Accept", "application/json")
                .header("Content-Type","application/json")
                .log()
                .all()
                .body(orders)
                .when()
                .post("/api/ecom/order/create-order")
                .then()
                .log()
                .all()
                .statusCode(201);

    }

    @Test
    public void deleteProduct(){
        JsonPath resJS = ReUsableMethod.JsonConvert(String.valueOf(response1));
        String productID = resJS.get("productId");
        RequestSpecification baseSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("authorization", token).setContentType(ContentType.JSON)
                .build();
                given().spec(baseSpecification)
                .when()
                .delete("api/ecom/product/delete-product/"+productID)
                .then()
                .log()
                .all()
                .statusCode(200);
    }


}
