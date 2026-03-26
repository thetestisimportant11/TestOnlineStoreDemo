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
        mainPage.checkNameAfterLogin();
    }
    @Test
    public void testNotCorrectEmail(){
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.openLoginPage().auth("test+2@gmail", "test");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.checkNotificationFailAuth();
    }
}
