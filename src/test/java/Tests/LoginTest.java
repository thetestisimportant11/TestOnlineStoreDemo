package Tests;

import Core.SeleniumForTest;
import Pages.LoginPage;
import Pages.MainPage;
import org.junit.jupiter.api.Test;

class LoginTest extends SeleniumForTest {

    @Test
    public void successLogin() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage().auth("test+2@gmail.com", "test");
        System.out.println("Для проверки при входе в аккаунт введены валидные данные.");
        mainPage.checkNameAfterLogin();
    }
    @Test
    public void emptyLoginPassword() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage().auth("", "");
        System.out.println("Для проверки поля пароль и e-mail оставлены пустыми при входе.");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.checkNotificationFailAuth();
    }
    @Test
    public void notCorrectEmail(){
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage().auth("test+2@gmail", "test");
        System.out.println("Для проверки введен НЕВАЛИДНЫЙ e-mail.");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.checkNotificationFailAuth();
    }
    @Test
    public void notCorrectPassword(){
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage().auth("test+2@gmail", "t");
        System.out.println("Для проверки введен НЕВЕРНЫЙ пароль.");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.checkNotificationFailAuth();
    }
    @Test
    public void notRegisteredEmail(){
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage().auth("test+1111@gmail.com", "test");
        System.out.println("Для проверки введен e-mail на который регистрация НЕ проводилась.");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.checkNotificationFailAuth();
    }
    @Test
    public void isPasswordMasking() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage().auth("test+2@gmail.com", "test");
        System.out.println("Для проверки при входе в аккаунт введены валидные данные.");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.checkPasswordMasking();
        System.out.println("При входе значение поля 'Пароль' замаскировано");

    }
    @Test
    public void isVisibleElementsEntryWindow(){
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.checkWindowEntryUIElementsVisibility();
    }
}
