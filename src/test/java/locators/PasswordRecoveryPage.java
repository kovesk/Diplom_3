package locators;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends WaitPage {

    private final By forgotPasswordLabel = By.xpath("//h2[text() = 'Восстановление пароля']");
    private final By enterButton = By.xpath("//a[text() = 'Войти']");
    public PasswordRecoveryPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание видимости элемента: Восстановление пароля")
    public void waitForLoad() {
        waitForVisibility(forgotPasswordLabel);
    }

    @Step("Нажать на кнопку Войти")
    public void clickEnterButton() {
        webDriver.findElement(enterButton).click();
    }
}
