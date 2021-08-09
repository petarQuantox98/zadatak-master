package NIRI.SiteTools;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class JsonReadWrite extends BaseClass {


    public static synchronized Map<String, Object> parseDeviceTypeJSONObject(String JSONPath, String deviceType)
    {
        //Get custom device parameters

        JSONParser parser = new JSONParser();
        Map<String, Object> deviceParameters = new HashMap<>();
        try {

            JSONObject deviceObject =  (JSONObject) parser.parse(new FileReader(JSONPath));

            if (deviceObject.containsKey(deviceType)) {
                JSONObject value = (JSONObject) deviceObject.get(deviceType);
                deviceParameters.put("width", value.get("width"));
                deviceParameters.put("height", value.get("height"));
                deviceParameters.put("pixelRatio", value.get("pixelRatio"));
                deviceParameters.put("ua", value.get("ua"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("JSON could not be parsed properly");;
        }
        return deviceParameters;
    }


}
