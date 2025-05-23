import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;

public class BaseTest {
    protected AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.enoiu.todo");
        caps.setCapability("appActivity", "com.enoiu.todo.MainActivity");
        caps.setCapability("noReset", true);

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}