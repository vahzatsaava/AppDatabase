package company.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfigReader {
    private DatabaseConfigReader() {

    }

    public static String getConfig(ConfigParametersDB parameters) throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("src/main/resources/liquibase.properties");
        properties.load(inputStream);

        return properties.getProperty(parameters.name().toLowerCase());
    }

}
