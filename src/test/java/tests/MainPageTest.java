package tests;

import io.qameta.allure.junit4.DisplayName;
import locators.MainPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static constants.Errors.*;

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

        Assert.assertTrue(SAUCE_BUT_NOT_WORK, mainPage.isSauceSectionSelected());
    }

    @Test
    @DisplayName("Кнопки: булки")
    public void testBunSectionButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickSauceButton();
        mainPage.clickBunButton();

        Assert.assertTrue(BUN_BUT_NOT_WORK, mainPage.isBunSectionSelected());
    }

    @Test
    @DisplayName("Кнопка: начинки")
    public void testFillingSectionButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickFillingButton();

        Assert.assertTrue(FILL_BUT_NOT_WORK, mainPage.isFillingSectionSelected());
    }
}