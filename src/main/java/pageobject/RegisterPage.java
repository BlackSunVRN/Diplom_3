package pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    //Локатор полей Имя, Email и Пароль
    @FindBy(how = How.CLASS_NAME, using = "input__textfield")
    private ElementsCollection inputs;

    //Локатор кнопки Зарегистрироваться
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registerButton;

    //Локатор подписи Некорректный пароль
    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement invalidPasswordText;

    //Локатор ссылки на страницу логина кнопкой Войти
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginLink;

    @Step("Заполнение поля Имя")
    public void setName(String inputName) {
        inputs.get(0).setValue(inputName);
    }

    @Step("Заполнение поля Email")
    public void setEmail(String inputEmail) {
        inputs.get(1).setValue(inputEmail);
    }

    @Step("Заполнение поля Пароль")
    public void setPassword(String inputPassword) {
        inputs.get(2).setValue(inputPassword);
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Нажатие кнопки зарегистрироваться и ожидание страницы логина")
    public void clickRegisterButtonForLogin() {
        registerButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
    }

    @Step("Нажатие кнопки Войти и ожидание страницы логина")
    public LoginPage clickLogin() {
        loginLink.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    @Step("Сравнение текста с ожидаемым")
    public void compareText(String expectedText) {
        invalidPasswordText.shouldBe(visible).shouldHave(exactText(expectedText));
    }

    @Step("Ожидание загрузки страницы")
    public void waitForLoadRegisterPage() {
        $(byText("Регистрация")).shouldBe(visible);
    }
}
