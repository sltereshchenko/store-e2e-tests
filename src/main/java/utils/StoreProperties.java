package utils;

import opm.browser.WebDriverManager;
import opm.os.OSType;

import java.io.FileReader;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;

@SuppressWarnings("ALL")
public class StoreProperties {
    public static final OSType OS = OSType.getOS();
    private static final String PROPERTIES_FILENAME = "store-e2e-tests.properties";
    private static final Properties PROPERTIES = loadProperties(PROPERTIES_FILENAME);
    public static final WebDriverManager.Browsers BROWSER = WebDriverManager.Browsers.getBrowser(PROPERTIES.getProperty("BROWSER"));
    private static final String PROTOCOL = PROPERTIES.getProperty("PROTOCOL");
    private static final String HOST = PROPERTIES.getProperty("HOST");
    public static final String URL = String.format("%s://%s", PROTOCOL, HOST);

    private StoreProperties() {
    }

    public static Properties loadProperties(String file) {
        Properties properties = new Properties();
        try {
            FileReader fr = new FileReader(file);
            properties.load(fr);
        } catch (IOException e) {
            throw new MissingResourceException("Exception when try read property file:" + e.getMessage(), file, file);
        }
        return properties;
    }
}
