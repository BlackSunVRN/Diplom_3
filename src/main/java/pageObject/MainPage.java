package pageObject;

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


public class MainPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //Локатор кнопок в шапке
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__linkText__3q_va")
    private ElementsCollection headerButtons;

    //Локатор кнопки Войти в аккаунт
    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    //Локатор вкладок Булки, Соусы, Начинки
    @FindBy(how = How.CLASS_NAME, using = "tab_tab__1SPyG")
    private ElementsCollection tabs;

    //Локатор открытого таба
    @FindBy(how = How.CLASS_NAME, using = "tab_tab_type_current__2BEPc")
    private SelenideElement currentTab;

    @Step("Нажатие кнопки Войти в аккаунт")
    public LoginPage clickLoginButton() {
        loginButton.click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    @Step("Нажатие кнопки Личный кабинет до авторизации")
    public LoginPage clickProfileButton() {
        headerButtons.get(2).click();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.waitForLoadLoginPage();
        return loginPage;
    }

    @Step("Нажатие кнопки Личный кабинет после авторизации")
    public ProfilePage clickPersonalAccountButton() {
        headerButtons.get(2).click();
        ProfilePage profilePage = Selenide.page(ProfilePage.class);
        profilePage.waitForLoadProfilePage();
        return profilePage;
    }

    @Step("Нажатие кнопки Булки")
    public void clickBunsTab() {
        tabs.get(0).click();
    }

    @Step("Нажатие кнопки Соусы")
    public void clickSaucesTab() {
        tabs.get(1).click();
    }

    @Step("Нажатие кнопки Начинки")
    public void clickFillingsTab() {
        tabs.get(2).click();
    }

    @Step("Сравнение текста с ожидаемым")
    public void compareText(String expectedText) {
        currentTab.shouldHave(exactText(expectedText));
    }

    @Step("Ожидание загрузки страницы")
    public void waitForLoadMainPage() {
        $(byText("Соберите бургер")).shouldBe(visible);
    }
}
