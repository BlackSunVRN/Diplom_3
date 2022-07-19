import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static pageObject.LoginPage.LOGIN_PAGE_URL;
import static pageObject.MainPage.MAIN_PAGE_URL;

public class LoginTest {

    public static final String TEST_EMAIL = "testBoldin@ya.ru";
    public static final String TEST_PASSWORD = "password123";
    MainPage mainPage;

    LoginPage loginPage;

    RegisterPage registerPage;

    ForgotPasswordPage forgotPasswordPage;

    ProfilePage profilePage;

    @Before
    public void init() {
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void logout() {
        profilePage = mainPage.clickPersonalAccountButton();
        profilePage.clickLogout();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка логина через кнопку входа на главной странице")
    public void shouldBeLoginByMainPageLoginButton() {
        loginPage = mainPage.clickLoginButton();
        loginPage.setEmail(TEST_EMAIL);
        loginPage.setPassword(TEST_PASSWORD);
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка логина через кнопку входа в профиле")
    public void shouldBeLoginByProfileLoginButton() {
        loginPage = mainPage.clickProfileButton();
        loginPage.setEmail(TEST_EMAIL);
        loginPage.setPassword(TEST_PASSWORD);
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка логина через кнопку входа на форме регистрации")
    public void shouldBeLoginByRegisterLoginButton() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        loginPage = registerPage.clickLogin();
        loginPage.setEmail(TEST_EMAIL);
        loginPage.setPassword(TEST_PASSWORD);
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка логина через кнопку входа на странице восстановления пароля")
    public void shouldBeLoginByForgotPasswordLoginButton() {
        loginPage = mainPage.clickLoginButton();
        forgotPasswordPage = loginPage.clickForgotPassword();
        loginPage = forgotPasswordPage.clickLogin();
        loginPage.setEmail(TEST_EMAIL);
        loginPage.setPassword(TEST_PASSWORD);
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }
}
