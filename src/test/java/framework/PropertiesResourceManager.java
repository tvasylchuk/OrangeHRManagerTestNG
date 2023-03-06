package framework;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesResourceManager {

    private final Properties properties;
    public PropertiesResourceManager(String fileName) {
        this.properties = initProperties(fileName);
    }

    private Properties initProperties (String fileName){
        var _properties = new Properties();

        try {
            InputStream reader = getClass().getClassLoader().getResourceAsStream(fileName);
            _properties.load(reader);
            assert reader != null;
            reader.close();
            return _properties;
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getPropertyValueByKey(String key) {
        return properties.getProperty(key);
    }

    public String getPropertyValueByKey(String key, String DEFAULT_VALUE) {
        var result = properties.getProperty(key);
        return result == null ? DEFAULT_VALUE : result;
    }
}
