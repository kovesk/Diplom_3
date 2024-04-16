package tests;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import drivers.Drivers;

import java.time.Duration;

public class DriverCheck extends ExternalResource {

    private WebDriver webDriver;

    // Чтение передаваемого параметра browser (-Dbrowser)
    String env = System.getProperty("browser", "chrome");

    @Override
    protected void before() {
        webDriver = Drivers.getDriver(env.toLowerCase());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    protected void after() {
        webDriver.quit();
    }
}
