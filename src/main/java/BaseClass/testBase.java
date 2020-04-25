package BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class testBase {

    public Properties prop;

    public testBase() throws IOException {
        prop = new Properties();

        FileInputStream fis = new FileInputStream("D:\\RestApiAutomationProjectFolder\\src\\main\\java\\configProperties\\config.properties");
            prop.load(fis);
    }
}
