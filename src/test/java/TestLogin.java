import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin extends BazaTesta {

    @Test
    public void testSuccessfulLogin() {
        driver.get("https://mega.readyscript.ru/");
        System.out.println("Страница загружена");

        navigateToLoginPage();
        performLogin("demo@example.com", "demo");

        // Проверяем успешность входа
        assertTrue(isLoginSuccessful(), "Вход в аккаунт не был осуществлён");

        // Выход из системы
        WebElement userElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), 'Артем Иванов')]")));
        userElement.click();
        WebElement quitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='aside-menu__link lk-logout']")));
        quitButton.click();
        System.out.println("Выход из личного кабинета выполнен");
    }

    @Test
    public void testNotSuccessfulLogin() {
        driver.get("https://mega.readyscript.ru/");
        System.out.println("Страница загружена");

        navigateToLoginPage();
        performLogin("", "");

        assertTrue(isLoginNotSuccessful(), "Вход в аккаунт был осуществлен");
    }
}