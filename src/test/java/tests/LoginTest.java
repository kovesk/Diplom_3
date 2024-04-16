package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import api.UserMainApi;
import requests.UserCreds;
import locators.PasswordRecoveryPage;
import locators.LoginPage;
import locators.MainPage;
import locators.RegistrationPage;

import static constants.Errors.*;

public class LoginTest {

    @Rule
    public DriverCheck driverCheck = new DriverCheck();
    private UserMainApi api;
    private UserCreds userCreds;
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriver webDriver = driverCheck.getWebDriver();
        api = new UserMainApi();
        userCreds = CredsGenerator.createRandomUser();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        passwordRecoveryPage = new PasswordRecoveryPage(webDriver);
        accessToken = api.userCreate(userCreds);
    }

    @Test
    @DisplayName("Логин. Кнопка Войти в аккаунт")
    public void testLoginByAccountButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userCreds.getEmail(), userCreds.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue(LOG_BUT_FAIL, mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Логин. Кнопка личный кабинет")
    public void testLoginByProfileButton() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterProfileLink();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userCreds.getEmail(), userCreds.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue(LOG_BUT_FAIL, mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Логин. Через кнопку войти на странице регистрации")
    public void testLoginThroughRegistrationPage() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.clickRegisterLink();
        registrationPage.waitForLoad();
        registrationPage.clickLoginLink();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userCreds.getEmail(), userCreds.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue(LOG_REG_FAIL, mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Логин. Через кнопку войти на странице Восстановить пароль")
    public void testLoginThroughPasswordRecoveryPage() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.waitForLoad();
        loginPage.clickForgotPasswordLink();
        passwordRecoveryPage.waitForLoad();
        passwordRecoveryPage.clickEnterButton();
        loginPage.waitForLoad();
        loginPage.enterLoginData(userCreds.getEmail(), userCreds.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue(LOG_PASS_FAIL, mainPage.isOrderButtonVisible());
    }

    @After
    public void tearDown() {
        api.userDelete(accessToken);
    }
}
