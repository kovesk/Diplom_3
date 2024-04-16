package locators;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends WaitPage {

    private final By registerLink = By.xpath("//div[@id=\"root\"]//p[1]/a\n");
    private final By emailField = By.xpath("//label[text()='Email']/../input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/../input");
    private final By forgotPasswordLink = By.xpath("//a[text() = 'Восстановить пароль']");
    private final By constructorButton = By.xpath("//p[text() = 'Конструктор']");
    private final By logoButton = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a");
    private final By enterButton = By.xpath("//button[text() = 'Войти']");
    private final By enterHeader = By.xpath("//h2[text() = 'Вход']");


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    @Step("Ожидание отображения элемента: Зарегистрироваться")
    public void waitForLoad() {
        waitForVisibility(registerLink);
    }

    @Step("Нажать на элемент: Зарегистрироваться")
    public void clickRegisterLink() {
        webDriver.findElement(registerLink).click();
    }

    @Step("Нажать на элемент: Войти")
    public void clickEnterButton() {
        webDriver.findElement(enterButton).click();
    }

    @Step("Нажать на элемент: Восстановить пароль")
    public void clickForgotPasswordLink() {
        webDriver.findElement(forgotPasswordLink).click();
    }

    @Step("Ввод почты и пароля")
    public void enterLoginData(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Проверка видимости элемента")
    public boolean isEnterHeaderVisible() {
        return webDriver.findElement(enterHeader).isDisplayed();
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }

    @Step("Ввод почты")
    public void enterEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    @Step("Нажать на элемент: Конструктор")
    public void clickConstructorButton() {
        webDriver.findElement(constructorButton).click();
    }

    @Step("Нажать на элемент: Логотип")
    public void clickLogoButton() {
        webDriver.findElement(logoButton).click();
    }
}
