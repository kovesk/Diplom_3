package tests;


import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import api.UserMainApi;
import requests.UserCreds;
import locators.LoginPage;
import locators.MainPage;
import locators.ProfilePage;

import static constants.Errors.*;

public class ProfilePageTest {

    @Rule
    public DriverCheck driverCheck = new DriverCheck();
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private UserMainApi api;
    private UserCreds userCreds;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriver webDriver = driverCheck.getWebDriver();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        profilePage = new ProfilePage(webDriver);
        api = new UserMainApi();
        userCreds = CredsGenerator.createRandomUser();
        accessToken = api.userCreate(userCreds);
    }

    @Test
    @DisplayName("Кнопка: Личный кабинет")
    public void testProfileButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();

        Assert.assertTrue(PROFILE_LNK_NOT_WORK, loginPage.isEnterHeaderVisible());
    }

    @Test
    @DisplayName("Кнопка: Лого сайта")
    public void testLogoButton() throws InterruptedException {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();
        Thread.sleep(10000);
        loginPage.clickLogoButton();
        mainPage.waitForLoad();

        Assert.assertTrue(LOGO_BUT_NOT_WORK, mainPage.isCreateOrderHeaderVisible());
    }

    @Test
    @DisplayName("Проверка кнопки: Конструктор")
    public void testConstructorButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();
        loginPage.clickConstructorButton();
        mainPage.waitForLoad();

        Assert.assertTrue(CONSTR_BUT_NOT_WORK, mainPage.isCreateOrderHeaderVisible());
    }

    @Test
    @DisplayName("Проверка кнопки: Выход")
    public void testLogOutButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userCreds.getEmail(), userCreds.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();
        mainPage.isCreateOrderHeaderVisible();
        mainPage.clickEnterProfileLink();
        profilePage.waitForLoad();
        profilePage.clickLogoutButton();
        loginPage.waitForLoad();

        Assert.assertTrue(LOGO_BUT_NOT_WORK, loginPage.isEnterHeaderVisible());
    }

    @After
    public void tearDown() {
        api.userDelete(accessToken);
    }
}
