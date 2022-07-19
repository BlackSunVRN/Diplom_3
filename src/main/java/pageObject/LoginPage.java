package pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    //Локатор полей Email и Пароль
    @FindBy(how = How.CLASS_NAME, using = "input__textfield")
    private ElementsCollection inputs;

    //Локатор кнопки Войти
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    //Локатор ссылок на страницы Зарегистрироваться и Восстановить пароль
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private ElementsCollection linkText;

    @Step("Заполнение поля Email")
    public void setEmail(String email) {
        inputs.get(0).setValue(email);
    }

    @Step("Заполнение поля Пароль")
    public void setPassword(String password) {
        inputs.get(1).setValue(password);
    }

    @Step("Нажатие кнопки Войти")
    public MainPage clickLogin() {
        loginButton.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.waitForLoadMainPage();
        return mainPage;
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public RegisterPage clickRegister() {
        linkText.get(0).click();
        RegisterPage registerPage = Selenide.page(RegisterPage.class);
        registerPage.waitForLoadRegisterPage();
        return registerPage;
    }

    @Step("Нажатие кнопки Восстановить пароль")
    public ForgotPasswordPage clickForgotPassword() {
        linkText.get(1).click();
        ForgotPasswordPage forgotPasswordPage = Selenide.page(ForgotPasswordPage.class);
        forgotPasswordPage.waitForLoadForgotPasswordPage();
        return forgotPasswordPage;
    }

    @Step("Ожидание загрузки страницы")
    public void waitForLoadLoginPage() {
        $(byText("Вход")).shouldBe(visible);
    }
}
