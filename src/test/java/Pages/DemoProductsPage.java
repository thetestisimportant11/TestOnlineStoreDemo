package Pages;

import Core.SeleniumForPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoProductsPage extends SeleniumForPage {

    @FindBy (xpath = "//button[@data-href='/cart/?add=46&offer_id=1']")
    private WebElement buttonAddProductCart;
    @FindBy (xpath = "//button[@data-href='/cart/?add=46&offer_id=2']")
    private WebElement buttonAddProductCart2;

    @FindBy (xpath = "//label[@for='1-46--2']")
    private WebElement buttonSelectTypeProduct;

    @FindBy (xpath = "//a[text()='Перейти в корзину']")
    private WebElement buttonGoCart;


    public DemoProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        driver.get("https://mega.readyscript.ru/catalog/demo-produkty/");
        System.out.println("Открыта страница Демо-продукты.");
        PageFactory.initElements(driver, this);
    }
    public void addProduct() {
        try{
            waitForClickable(buttonAddProductCart).click();
            System.out.println("Кнопка добавления товара в корзину нажата");
        } catch (TimeoutException e) {
            System.err.println("Кнопка добавления товара в корзину НЕ нажата в течение 10 секунд");
        }
    }
    public void checkAddProduct() {
        try{
            waitForVisible(buttonGoCart);
            System.out.println("Появилось всплывающее окно корзины");
        } catch (TimeoutException e) {
            System.err.println("Всплывающее окно корзины не появилось в течение 10 секунд");
            throw new AssertionError("Всплывающее окно корзины не появилось", e);
        }
    }
    public void moveButtonAddProduct() {
        try{
            waitForClickable(buttonAddProductCart);
            actions.moveToElement(buttonAddProductCart).perform();
            System.out.println("Наведение на кнопку 'Добавить в корзину' - успешно");

            waitForClickable(buttonSelectTypeProduct);
            actions.moveToElement(buttonSelectTypeProduct).perform();
            waitForClickable(buttonSelectTypeProduct).click();
            System.out.println("Нажатие на кнопку выбора разновидности товара - успешно");
            waitForClickable(buttonAddProductCart2).click();
            System.out.println("Кнопка добавления товара в корзину нажата");
        } catch (TimeoutException e) {
            System.err.println("Всплывающее окно для выбора разновидности товара НЕ появилось в течение 10 секунд");
            throw new AssertionError("Всплывающее окно для выбора разновидности товара НЕ появилось", e);
        }
    }
}