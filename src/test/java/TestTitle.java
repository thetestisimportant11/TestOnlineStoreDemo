import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTitle {
    public WebDriver driver;

    @BeforeEach
    public void SetUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void checkTitle () throws InterruptedException {
        driver.get("https://mega.readyscript.ru/");
        Thread.sleep(2000);
        String expectedResult = "ReadyScript Гипермаркет - демонстрационный интернет магазин";
        String actualResult = driver.getTitle();
        assertEquals(expectedResult, actualResult, "Название не соответствует ожиданию!");
    }

    @AfterEach
    public void tearDown (){
        driver.quit();
    }

}
