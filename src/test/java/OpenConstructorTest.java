import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static pageobject.LoginPage.LOGIN_PAGE_URL;
import static pageobject.MainPage.MAIN_PAGE_URL;

public class OpenConstructorTest {

    public static final String TEST_EMAIL = "testBoldin@ya.ru";
    public static final String TEST_PASSWORD = "password123";

    MainPage mainPage;

    LoginPage loginPage;

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
    @DisplayName("Проверка перехода на главную страницу со страницы профиля кнопкой Конструктор")
    public void shouldOpenMainPageAfterClickConstructorButton() {
        loginPage = mainPage.clickProfileButton();
        loginPage.setEmail(TEST_EMAIL);
        loginPage.setPassword(TEST_PASSWORD);
        mainPage = loginPage.clickLogin();
        profilePage = mainPage.clickPersonalAccountButton();
        mainPage = profilePage.clickConstructor();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу со страницы профиля кнопкой с логотипом StellarBurgers")
    public void shouldOpenMainPageAfterClickStellarBurgersButton() {
        loginPage = mainPage.clickProfileButton();
        loginPage.setEmail(TEST_EMAIL);
        loginPage.setPassword(TEST_PASSWORD);
        mainPage = loginPage.clickLogin();
        profilePage = mainPage.clickPersonalAccountButton();
        mainPage = profilePage.clickStellarBurgersLogo();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(MAIN_PAGE_URL, currentUrl);
    }
}
