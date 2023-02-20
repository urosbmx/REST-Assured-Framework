package files;

import java.util.Random;

public class addNewBook {
    public static String addBooks(String name, Random author){
        String payLoad = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with "+name+"\",\n" +
                "\"isbn\":\"bcd\",\n" +
                "\"aisle\":\""+author+"\",\n" +
                "\"author\":\"John"+name+"\"\n" +
                "}\n";
        return payLoad;
    }
    public static String addBookss(String name, String author){
        String payLoad = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with "+name+"\",\n" +
                "\"isbn\":\"bcd\",\n" +
                "\"aisle\":\""+author+"\",\n" +
                "\"author\":\"John"+name+"\"\n" +
                "}\n";
        return payLoad;
    }
}
