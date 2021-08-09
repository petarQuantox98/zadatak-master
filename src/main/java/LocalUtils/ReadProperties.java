package LocalUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class ReadProperties {

    public ReadProperties(){
    }

    /**
     * Gets property file on given path and provides value for given key
     *
     * @return String
     */
    public String getPropertyValue(String key, String filePath) {

        Properties prop = null;
        try {
            InputStream input = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }

}
