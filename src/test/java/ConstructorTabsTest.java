import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static pageobject.MainPage.MAIN_PAGE_URL;

public class ConstructorTabsTest {

    MainPage mainPage;

    @Before
    public void init() {
        Configuration.startMaximized = true;
        mainPage = open(MAIN_PAGE_URL, MainPage.class);
    }

    @Test
    @DisplayName("Проверка открытие вкладки Соусы")
    public void shouldOpenSaucesTab() {
        mainPage.clickSaucesTab();
        mainPage.compareText("Соусы");
    }

    @Test
    @DisplayName("Проверка открытие вкладки Начинки")
    public void shouldOpenFillingsTab() {
        mainPage.clickFillingsTab();
        mainPage.compareText("Начинки");
    }

    @Test
    @DisplayName("Проверка открытие вкладки Булки")
    public void shouldOpenBunsTab() {
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        mainPage.compareText("Булки");
    }
}
