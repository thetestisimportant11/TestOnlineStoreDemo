package Pages;

import Core.SeleniumForPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends SeleniumForPage {

    @FindBy(xpath = "//span[contains(text(), 'Личный кабинет')]")
    private WebElement buttonCabinet;

    @FindBy(xpath = "//a[contains(text(), 'Вход')]")
    private WebElement buttonEntryCabinet;

    @FindBy(xpath = "//a[contains(text(),'Регистрация')]")
    private WebElement buttonRegisterationCabinet;

    @FindBy (xpath = "//span[contains(text(), 'test test')]")
    private WebElement buttonName;

    @FindBy (xpath = "//button//span[text()='Каталог']")
    private WebElement buttonCatalog;

    @FindBy (xpath = "//span[text()='Демо-продукты']")
    private WebElement buttonDemoProducts;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        driver.get("https://mega.readyscript.ru/");
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage() {
        waitForClickable(buttonCabinet).click();
        waitForClickable(buttonEntryCabinet).click();
        return new LoginPage(driver, wait);
    }
    public void checkNameAfterLogin() {
        try {
            waitForVisible(buttonName);
            System.out.println("Вход в аккаунт выполнен.");

        } catch (TimeoutException e) {
            System.out.println("Вход в аккаунт НЕ выполнен");
        }
    }
    public DemoProductsPage openDemoProductsPage() {
        waitForClickable(buttonCatalog).click();
        waitForClickable(buttonDemoProducts).click();
        return new DemoProductsPage(driver, wait);
    }
}
