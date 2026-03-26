package Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

abstract public class SeleniumForTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Браузер успешно закрыт.");
            } catch (Exception e) {
                System.err.println("Ошибка во время закрытия браузера: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Нечего закрывать.");
        }
    }
}
