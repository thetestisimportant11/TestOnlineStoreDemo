package Pages;

import Core.SeleniumForPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends SeleniumForPage {

    @FindBy(id = "input-auth1")
    private WebElement fieldEmail;

    @FindBy(id = "input-auth2")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button[contains(text(), 'Войти')]")
    private WebElement buttonEntry;

    @FindBy (xpath = "//div[contains(text(),'Неверный e-mail или пароль')]")
    private WebElement notificationFailAuth;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void auth(String email, String password) {
        waitForClickable(fieldEmail).click();
        fieldEmail.clear();
        fieldEmail.sendKeys(email);

        waitForClickable(fieldPassword).click();
        fieldPassword.clear();
        fieldPassword.sendKeys(password);

        waitForClickable(buttonEntry).click();
    }
    public void checkNotificationFailAuth() {
        try {
            waitForVisible(notificationFailAuth);
            System.out.println("Уведомление 'Неверный e-mail или пароль' отображено");
        } catch (TimeoutException e) {
            System.err.println("Ошибка: Уведомление не появилось в течение 10 секунд");
            throw new AssertionError("Уведомление об ошибке аутентификации не отобразилось", e);
        }
    }
}