package pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Локатор кнопки Выход
    @FindBy(how = How.CLASS_NAME, using = "Account_button__14Yp3")
    private SelenideElement exitButton;

    //Локатор кнопки Конструктор
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__linkText__3q_va")
    private SelenideElement constructorLinkText;

    //Локатор кнопки StellarBurgers
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgersLogo;

    @Step("Нажатие кнопки Выход")
    public LoginPage clickLogout() {
        exitButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    @Step("Нажатие кнопки Конструктор")
    public MainPage clickConstructor() {
        constructorLinkText.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.waitForLoadMainPage();
        return mainPage;
    }

    @Step("Нажатие на логотип с текстом StellarBurgers")
    public MainPage clickStellarBurgersLogo() {
        stellarBurgersLogo.click();
        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.waitForLoadMainPage();
        return mainPage;
    }

    @Step("Ожидание загрузки страницы")
    public void waitForLoadProfilePage() {
        $(byText("Выход")).shouldBe(visible);
    }
}
