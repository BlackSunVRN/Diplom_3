import client.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static model.User.getRandomUser;
import static org.junit.Assert.assertEquals;
import static pageobject.LoginPage.LOGIN_PAGE_URL;
import static pageobject.MainPage.MAIN_PAGE_URL;

public class RegisterTest {

    MainPage mainPage;

    RegisterPage registerPage;

    LoginPage loginPage;

    User user;
    UserClient userClient;

    @Before
    public void init() {
        user = getRandomUser();
        userClient = new UserClient();
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @After
    public void deleteUser() {
        try {
            userClient.deleteUser(user);
        } catch (IllegalArgumentException e) {
            System.out.println("Пользователь не создан. Все ок.");
        }
    }
    @Test
    @DisplayName("Проверка регистрации нового пользователя")
    public void shouldBeSuccessRegister() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButtonForLogin();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
        closeWindow();
    }

    @Test
    @DisplayName("Проверка отображения ошибки, если пароль менее 6 символов")
    public void shouldBeErrorWithTextShortPassword() {
        loginPage = mainPage.clickLoginButton();
        registerPage = loginPage.clickRegister();
        // Устанавливаю некорректный пароль для юзера, созданного в тесте,
        // чтобы потом, в случае создания пользователя из-за ошибки, можно было удалять его
        // в @After вместе со всеми другими пользователями, которые создаются для тестов,
        // т.к. количество тестов может увеличиваться, а отдельно в каждом удалять не ок
        user.setPassword(RandomStringUtils.randomAlphabetic(5));
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();
        registerPage.compareText("Некорректный пароль");
        closeWindow();
    }
}
