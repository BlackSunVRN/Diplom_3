import client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static pageobject.LoginPage.LOGIN_PAGE_URL;
import static pageobject.MainPage.MAIN_PAGE_URL;

import static model.User.getRandomUser;

public class LoginTest {
    MainPage mainPage;

    LoginPage loginPage;

    RegisterPage registerPage;

    ForgotPasswordPage forgotPasswordPage;

    ProfilePage profilePage;

    User user;

    UserClient userClient;

    @Before
    public void init() {
        user = getRandomUser();
        userClient = new UserClient();
        userClient.createUser(user);
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void logout() {
        profilePage = mainPage.clickPersonalAccountButton();
        profilePage.clickLogout();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
        closeWindow();
        userClient.deleteUser(user);
    }

    @Test
    @DisplayName("Проверка логина через кнопку входа на главной странице")
    public void shouldBeLoginByMainPageLoginButton() {
        loginPage = mainPage.clickLoginButton();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка логина через кнопку входа в профиле")
    public void shouldBeLoginByProfileLoginButton() {
        loginPage = mainPage.clickProfileButton();
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
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
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
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
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        mainPage = loginPage.clickLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }
}
