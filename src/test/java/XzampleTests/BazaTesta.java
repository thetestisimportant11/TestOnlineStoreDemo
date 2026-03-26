package XzampleTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

abstract class BazaTesta {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public void openWebSite(){
        driver.get("https://mega.readyscript.ru/");
        System.out.println("Страница загружена");
    }

    public void navigateToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Личный кабинет')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Вход')]"))).click();
    }
    public void navigateToRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Личный кабинет')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Регистрация')]"))).click();
    }

    public void performLogin(String email, String password) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth1")));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth2")));
        passwordField.clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Войти')]"))).click();
    }
    public void logOut(){
        WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), 'test test')]")));
        userElement.click();
        WebElement quitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='aside-menu__link lk-logout']")));
        quitButton.click();
        System.out.println("Выход из личного кабинета выполнен");
    }

    public void isLoginPasswordSuccessful() {
        try {
            WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'test test')]")));
            System.out.println("Вход в личный кабинет выполнен");
            userElement.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Вход в аккаунт не был осуществлён");

        }
    }

    public void isLoginOrPasswordNotSuccessful() {
        try {
            WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Неверный e-mail или пароль')]")));
            System.out.println("Система корректно сработала при входе с невалидными данными");
            userElement.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Вход в аккаунт был осуществлен c некорректными данными");
        }

    }

    public void isProductFounded() {
        try {
            WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(text(),' Результаты поиска')]")));
            System.out.println("Поиск выполнен, показан результат");
            userElement.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Поиск не сработал");
        }
    }
    public void isProductNotFounded() {
        try {
            WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'По вашему запросу ничего не найдено. Проверьте правильность введенного запроса')]")));
            System.out.println("Поиск выполнен, показан результат отсутствия искомого товара");
            userElement.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Что-то нашлось, но не должно было(");
        }
    }
    public void isCatalogIsVisible(){
        try{
            WebElement clickCatalogProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Выберите')][contains(text(),'категорию')]")));
            System.out.println("Каталог открыт, доступен выбор категории товаров");

            clickCatalogProduct.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Каталог не отобразился");
        }
    }

    public void isSuccessfulUsingCatalog(){
        try{
            WebElement checkOpenCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(text(),'Демо-продукты')]")));
            System.out.println("Выбранная категория отобразилась");
            checkOpenCategory.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Выбранная категория не отобразилась");
        }
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}
