package XzampleTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin extends BazaTesta {

    @Test
    public void testSuccessfulLogin(){
        openWebSite();
        navigateToLoginPage();
        performLogin("test+2@gmail.com", "test");

        isLoginPasswordSuccessful();// Проверяем успешность входа

        logOut();
    }

    @Test
    public void testEmptyLoginPassword() {
        openWebSite();
        navigateToLoginPage();
        performLogin("", "");
        isLoginOrPasswordNotSuccessful();
    }
    @Test
    public void testNotCorrectEmail(){
        openWebSite();
        navigateToLoginPage();
        performLogin("test+2@gmail", "test");
        isLoginOrPasswordNotSuccessful();
    }
    @Test
    public void testNotCorrectPassword(){
        openWebSite();
        navigateToLoginPage();
        performLogin("test+2@gmail.com", "t");
        isLoginOrPasswordNotSuccessful();
    }
    @Test
    public void testNotRegisteredEmail(){
        openWebSite();
        navigateToLoginPage();
        performLogin("test+1111@gmail.com", "test");
        isLoginOrPasswordNotSuccessful();
    }
    @Test
    public void testUIElementsVisibility() {
        openWebSite();
        navigateToLoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth1")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth2")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Забыли пароль?')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'У меня нет аккаунта')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Войти')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Close']")));
    }
    @Test
    public void testElementsClickable() {
        openWebSite();
        navigateToLoginPage();

            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-auth1")));
            emailField.click();
            emailField.clear();
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input-auth2")));
            passwordField.click();
            WebElement buttonEntry = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Войти')]")));
            buttonEntry.click();
            isLoginOrPasswordNotSuccessful();

            WebElement buttonClose = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Close']")));
            buttonClose.click();

        navigateToLoginPage();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Забыли пароль?')]"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Восстановление пароля']"))).isDisplayed();
            System.out.println("Переход к окну восстановления пароля выполнен после нажатия на кнопку 'Забыли пароль?'");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Вспомнили пароль?']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("input-auth1"))).isDisplayed();
            System.out.println("Возвращение к окну входа после нажатия на кнопку 'Вспомнили пароль?' выполнено");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'У меня нет аккаунта')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Регистрация']")));
            System.out.println("Открывается окно для регистрации при нажатии на кнопку - 'У меня нет аккаунта'");
    }
    @Test
    public void testPasswordMasking() {
        openWebSite();
        navigateToLoginPage();
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth2")));
        passwordField.clear();
        passwordField.sendKeys("test");

        String typeAttribute = passwordField.getAttribute("type");
        assertEquals("password", typeAttribute, "В поле 'Пароль' виден текст");
    }
}