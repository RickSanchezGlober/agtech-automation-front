package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

public class DataUtils {
    public static final Logger log = LogManager.getLogger(DataUtils.class);
    
    @DataProvider(name="readJson")
    public static Object[] readJson(){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            Object object = parser.parse(new FileReader("src/test/resources/data/testData.json"));
            jsonObject = (JSONObject) object;
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        Object[] data = new Object[1];

        // Store JSON data as key/value pairs in a hashMap
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        if (jsonObject != null) {
            Set<String> jsonObjKeys = jsonObject.keySet();
            for (String jsonObjKey : jsonObjKeys) {
                hashMap.put(jsonObjKey, (String) jsonObject.get(jsonObjKey));
            }
        } else {
            log.error("Error retrieving JSON data");
            throw new RuntimeException();
        }

        // Store HashMap in an array and return array
        data[0] = hashMap;
        return data;
    }
}