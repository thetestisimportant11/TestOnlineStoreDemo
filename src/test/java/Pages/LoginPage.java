package Pages;

import Core.SeleniumForPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends SeleniumForPage {

    @FindBy(id = "input-auth1")
    private WebElement fieldEmail;

    @FindBy(id = "input-auth2")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button[contains(text(), 'Войти')]")
    private WebElement buttonEntry;

    @FindBy (xpath = "//div[contains(text(),'Неверный e-mail или пароль')]")
    private WebElement notificationFailAuth;

    @FindBy (xpath = "//a[contains(text(),'Забыли пароль?')]")
    private WebElement buttonForgotPassword;

    @FindBy (xpath = "//div[text()='Восстановление пароля']")
    private WebElement buttonRecoverPassword;

    @FindBy (xpath = "//a[text()='Вспомнили пароль?']")
    private WebElement buttonRememberPassword;

    @FindBy (xpath = "//a[contains(text(),'У меня нет аккаунта')]")
    private WebElement buttonDoNtHaveAnAccount;

    @FindBy (xpath = "//button[@aria-label='Close']")
    private WebElement buttonCloseLoginWindow;


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
    public void checkPasswordMasking(){
        try {
            waitForVisible(fieldPassword);
            String typeAttribute = fieldPassword.getAttribute("type");
            assertEquals("password", typeAttribute, "Поле пароля не имеет типа 'password'");
            System.out.println("Атрибут type поля пароля — 'password' (маска активна)");
        } catch (AssertionError e) {
            System.err.println("Ошибка: " + e.getMessage());
            throw e;
        }
    }
    public void checkWindowEntryUIElementsVisibility(){
        try{
            waitForVisible(fieldPassword);
            waitForVisible(fieldEmail);
            waitForVisible(buttonEntry);
            waitForVisible(buttonForgotPassword);
            waitForVisible(buttonDoNtHaveAnAccount);
            System.out.println("Элементы отображены: Логин, Пароль, Кнопка Войти, Кнопка 'Забыли пароль?', Кнопка 'У меня нет аккаунта', кнопка 'Закрыть'");
        } catch (TimeoutException e){
            System.err.println("Ошибка: Элементы на окне входа не отображены в течение 10 секунд");
        }
    }
}