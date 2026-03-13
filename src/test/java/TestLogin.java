import org.junit.jupiter.api.Test;

public class TestLogin extends BazaTesta {

    @Test
    public void testSuccessfulLogin(){
        openWebSite();
        navigateToLoginPage();
        performLogin("test+2@gmail.com", "test");

        isLoginSuccessful();// Проверяем успешность входа

        logOut();
    }

    @Test
    public void testNotSuccessfulLogin() {
        openWebSite();
        navigateToLoginPage();
        performLogin("", "");
        isLoginNotSuccessful();
    }
}