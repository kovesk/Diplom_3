package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class WaitPage {

    protected final WebDriver webDriver;

    public WaitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Ожидание появления элемента")
    protected void waitForVisibility(By element) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(element)));
    }

    public abstract void waitForLoad();

}
