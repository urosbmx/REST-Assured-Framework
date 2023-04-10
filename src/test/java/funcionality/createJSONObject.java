package funcionality;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class createJSONObject {

    public static JsonNode jsonForGoogle(String name,String phone,String latitude, String longitude,String accuracy ){

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        ObjectNode location = objectMapper.createObjectNode();
        location.put("latitude",latitude);
        location.put("longitude",longitude);
        rootNode.put("location",location);
        rootNode.put("accuracy",accuracy);
        rootNode.put("name",name);
        rootNode.put("phone_number",phone);
        rootNode.put("address","29, side layout, cohen 09");
        rootNode.put("types","shoe park,shop");
        rootNode.put("website","http://google.com");
        rootNode.put("language","French-IN");
        return rootNode;
    }
    public static JsonNode errorGoogle(String msg){

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("msg",msg);
        return rootNode;
    }

    public static JsonNode bookResponse(String bookName,String isbn,String aisle,String author){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode rootNode = objectMapper.createArrayNode();
        ObjectNode boookResponse = objectMapper.createObjectNode();
        boookResponse.put("book_name",bookName);
        boookResponse.put("isbn",isbn);
        boookResponse.put("aisle",aisle);
        boookResponse.put("author",author);
        rootNode.add(boookResponse);
        return rootNode;
    }
}
