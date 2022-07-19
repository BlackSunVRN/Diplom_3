import client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;
import static pageObject.LoginPage.LOGIN_PAGE_URL;
import static pageObject.MainPage.MAIN_PAGE_URL;

public class RegisterTest {

    public static final String TEST_EMAIL = RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
    public static final String TEST_PASSWORD = RandomStringUtils.randomAlphabetic(8);

    MainPage mainPage;

    RegisterPage registerPage;

    LoginPage loginPage;

    User user;
    UserClient userClient;

    @Before
    public void init() {
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
        userClient = new UserClient();
        user = new User(TEST_EMAIL, TEST_PASSWORD);
    }

    @After
    public void deleteUser() {

    }

    @Test
    @DisplayName("Проверка регистрации нового пользователя")
    public void shouldBeSuccessRegister() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        registerPage.setName(RandomStringUtils.randomAlphabetic(8));
        registerPage.setEmail(TEST_EMAIL);
        registerPage.setPassword(TEST_PASSWORD);
        registerPage.clickRegisterButtonForLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
        closeWindow();
        userClient.deleteUser(user);
    }

    @Test
    @DisplayName("Проверка отображения ошибки, если пароль менее 6 символов")
    public void shouldBeErrorWithTextShortPassword() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        registerPage.setPassword(RandomStringUtils.randomAlphabetic(5));
        registerPage.clickRegisterButton();
        registerPage.compareText("Некорректный пароль");
    }
}
