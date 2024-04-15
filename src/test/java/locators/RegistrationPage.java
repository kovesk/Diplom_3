package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends WaitPage {

    private final By registrationLabel = By.xpath("//h2[text()='Регистрация']");
    private final By nameField = By.xpath("//label[text()='Имя']/../input");
    private final By emailField = By.xpath("//label[text()='Email']/../input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/../input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By incorrectPasswordLabel = By.xpath("//p[text() = 'Некорректный пароль']");
    private final By enterButton = By.xpath("//a[text() = 'Войти']");

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание появления элемента: Регистрация")
    public void waitForLoad() {
        waitForVisibility(registrationLabel);
    }

    @Step("Создание нового аккаунта")
    public void enterNewAccountData(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Клик по элементу - Зарегистрироваться")
    public void clickRegisterButton() {
        webDriver.findElement(registerButton).click();
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод имя")
    public void enterName(String name) {
        webDriver.findElement(nameField).sendKeys(name);
    }

    @Step("Проверка видимости элемента - Некорректный пароль")
    public boolean isIncorrectPasswordLabelVisible() {
        return webDriver.findElement(incorrectPasswordLabel).isDisplayed();
    }

    @Step("Клик по элементу - Войти")
    public void clickLoginLink() {
        webDriver.findElement(enterButton).click();
    }
}
