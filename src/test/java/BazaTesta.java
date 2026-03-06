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

    public void navigateToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Личный кабинет')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Вход')]"))).click();
    }

    public void performLogin(String email, String password) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth1")));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id("input-auth2"));
        passwordField.clear();
        passwordField.sendKeys(password);

        driver.findElement(By.xpath("//button[contains(text(), 'Войти')]")).click();
    }

    public boolean isLoginSuccessful() {
        try {
            WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Артем Иванов')]")));
            System.out.println("Вход в личный кабинет выполнен");
            return userElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLoginNotSuccessful() {
        try {
            WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Неверный e-mail или пароль')]")));
            System.out.println("Система корректно сработала при входе с пустыми данными при входе");
            return userElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
