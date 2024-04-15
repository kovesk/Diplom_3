package locators;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends WaitPage {
    private final By logOutButton = By.xpath("//button[text() = 'Выход']");

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента")
    public void waitForLoad() {
        waitForVisibility(logOutButton);
    }

    @Step("Нажать на  элемент - Выход")
    public void clickLogoutButton() {
        webDriver.findElement(logOutButton).click();
    }
}
