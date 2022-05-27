package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

public class DataUtils {
    private static final String JSON_FILE = "src/test/resources/data/testData.json";
    private static final Logger log = LogManager.getLogger(DataUtils.class);
    
    @DataProvider(name="dataJson")
    public static Object[] dataJson(){
        return readJson(JSON_FILE, "data 1");
    }

    // This method uses the GSON library to parse JSON data
    public static Object[][] readJson(String filename, String jsonObj) {
        File file = new File(filename);
        JsonElement jsonElement = null;

        // Parse JSON data
        try {
            jsonElement = JsonParser.parseReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            log.error(e);
        }

        assert jsonElement != null;
        // Get entire JSON object
        JsonObject jsonObj1 = jsonElement.getAsJsonObject();
        // Get individual JSON array object
        JsonArray jsonArray = jsonObj1.get(jsonObj).getAsJsonArray();

        // Java array to store JSON data
        Object[][] testData = new Object[jsonArray.size()][1];

        // Read data inside JSON array and store it in Java array
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObj2 = jsonArray.get(i).getAsJsonObject();
            Map<Object, Object> map = new HashMap<>();

            for (String key : jsonObj2.keySet()) {
                String value = jsonObj2.get(key).getAsString();
                map.put(key, value);
            }
            testData[i][0] = map;
        }

        return testData;
    }
}