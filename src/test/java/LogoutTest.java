import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static pageObject.LoginPage.LOGIN_PAGE_URL;
import static pageObject.MainPage.MAIN_PAGE_URL;

public class LogoutTest {

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

    @Test
    @DisplayName("Проверка выхода из профиля после авторизации")
    public void shouldBeLogout() {
        loginPage = mainPage.clickLoginButton();
        loginPage.setEmail(TEST_EMAIL);
        loginPage.setPassword(TEST_PASSWORD);
        mainPage = loginPage.clickLogin();
        profilePage = mainPage.clickPersonalAccountButton();
        profilePage.clickLogout();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
    }
}
