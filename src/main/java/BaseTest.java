import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseTest {

    AppiumDriver driver;

    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.enoiu.todo");
        caps.setCapability("appActivity", "com.enoiu.todo.MainActivity");

        driver = new AndroidDriver(new URL("https://play.google.com/store/apps/details?id=com.enoiu.todo&pli=1"), caps);
        //http://localhost:4723/wd/hub
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
