import files.bigJSonData;
import io.restassured.path.json.JsonPath;

public class jsonBigData {

     public static void main(String[] args) {
         JsonPath js = new JsonPath(bigJSonData.bigJson());

         //Print all courses count
             int sumCourse = js.getInt("courses.size()");
             System.out.println(sumCourse);

         //Print purches amount

         int purchaseAmount = js.getInt("dashboard.purchaseAmount");
         System.out.println(purchaseAmount);

         //Print title of the first course

         String nameOfFirstCourse = js.getString("courses.title[0]");
         System.out.println(nameOfFirstCourse);

         // Print All course titles and price

         for (int i=0; i < sumCourse; i++){
             int priceOFCourse  = js.getInt("courses.price["+i+"]");
             String nameOFCOurse = js.getString("courses.title["+i+"]");
             System.out.println(nameOFCOurse+"  price"+"  "+priceOFCourse);
         }

         // Print RPA Course

         for (int i=0; i< sumCourse; i++){
            String coursName = js.getString("courses.title["+i+"]");
             if(coursName.contentEquals("RPA")){
                 System.out.println(js.getInt("courses["+i+"].copies"));
             }
         }

         //Checks sum of price
        int test = 0;
        for (int i=0; i< sumCourse; i++){
            int price = js.getInt("courses["+i+"].price");
            int count = js.getInt("courses["+i+"].copies");
            int sum = price*count;
            test = test+sum;
        }
        int amount = js.getInt("dashboard.purchaseAmount");
        if(test==amount){
            System.out.println("Iste su vrednosti");
        }else {
            System.out.println("Nije isto");
        }








    }
}
