package tests;

import io.qameta.allure.junit4.DisplayName;
import locators.MainPage;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MainPageTest {

    @Rule
    public DriverCheck driverCheck = new DriverCheck();
    private MainPage mainPage;

    @Before
    public void setUp() {
        WebDriver webDriver = driverCheck.getWebDriver();
        mainPage = new MainPage(webDriver);
    }

    @Test
    @DisplayName("Кнопки: соусы")
    public void testSauceButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickSauceButton();
    }

    @Test
    @DisplayName("Кнопки: булки")
    public void testBunSectionButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
    }

    @Test
    @DisplayName("Кнопка: начинки")
    public void testFillingSectionButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickFillingButton();
    }
}