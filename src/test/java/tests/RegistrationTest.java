package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import api.UserMainApi;
import requests.AuthCreds;
import requests.UserCreds;
import locators.LoginPage;
import locators.MainPage;
import locators.RegistrationPage;

import static constants.Errors.INVALID_PASS;
import static constants.Errors.SUC_REG_FAIL;

public class RegistrationTest {

    @Rule
    public DriverCheck driverCheck = new DriverCheck();
    private UserMainApi api;
    private UserCreds userCreds;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    @Before
    public void setUp() {
        WebDriver webDriver = driverCheck.getWebDriver();
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        api = new UserMainApi();
        userCreds = CredsGenerator.createRandomUser();
    }

    @Test
    @DisplayName("Успешная регистрации")
    public void testSuccessfulRegistration() {
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();
        loginPage.clickRegisterLink();
        registrationPage.waitForLoad();
        registrationPage.enterNewAccountData(
                userCreds.getName(),
                userCreds.getEmail(),
                userCreds.getPassword()
        );
        registrationPage.clickRegisterButton();
        loginPage.enterLoginData(userCreds.getEmail(), userCreds.getPassword());
        loginPage.clickEnterButton();
        mainPage.waitForLoad();

        Assert.assertTrue(SUC_REG_FAIL, mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Регистрации с невалидным паролем")
    public void testRegistrationWithInvalidPassword() {
        api.userCreate(userCreds);
        mainPage.openMainPage();
        mainPage.waitForLoad();
        mainPage.clickEnterAccountButton();

        loginPage.clickRegisterLink();
        registrationPage.waitForLoad();
        int maxInvalidPasswordLength = 5;
        registrationPage.enterNewAccountData(
                userCreds.getName(),
                userCreds.getEmail(),
                CredsGenerator.randomPassword(maxInvalidPasswordLength)
        );
        registrationPage.clickRegisterButton();

        Assert.assertTrue(INVALID_PASS,
                registrationPage.isIncorrectPasswordLabelVisible());
    }

    @After
    public void tearDown() {
        AuthCreds authCreds = new AuthCreds(
                userCreds.getEmail(),
                userCreds.getPassword());
        String accessToken = api.userAuth(authCreds);
        api.userDelete(accessToken);
    }
}
