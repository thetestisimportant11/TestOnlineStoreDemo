import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import javax.swing.*;

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
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth1")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth2")));
        WebElement forgotPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Забыли пароль?')]")));
        WebElement doNTHaveAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'У меня нет аккаунта')]")));
        WebElement buttonEntry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(), 'Войти')]")));
        WebElement buttonClose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Close']")));
    }
//    @Test
//    public void testPasswordMasking() {
//        openWebSite();
//        navigateToLoginPage();
//        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-auth2")));
//        passwordField.clear();
//        passwordField.sendKeys("test");
//
//        assertFalse(passwordField.equals("test")); // Значение не должно быть видно
//    }
}